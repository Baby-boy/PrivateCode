package com.glanway.iclock.util.sms;

import com.glanway.iclock.util.HttpUtils;
import com.google.common.collect.Maps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ponly.common.util.Dates;
import org.ponly.common.util.IOUtils;
import org.ponly.common.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 基于 ip400.cc / ipyy.com 的 SMS 服务实现
 * <p/>
 * 注: 该短信平台需要登录后, 在"内容报备"中添加报备格式, 否则提示成功但收不到, eg: 报备 "您的验证码是@【签名】"
 * <p/>
 * <a href="http://ip400.cc">ip400.cc</a>
 * <a href="http://sh2.ipyy.com:8080/">sms login</a>
 */
public class IpyySmsSenderImpl implements SmsSender {
    private static final Logger LOG = LoggerFactory.getLogger(IpyySmsSenderImpl.class);

    private static final String MOBILE_SEPARATOR = ",";
    private static final String DEFAULT_SMS_URL = "http://sh2.ipyy.com/sms.aspx";
    private static final String DEFAULT_MMS_URL = "http://sh2.ipyy.com/mms.aspx";
    private static final String DEFAULT_STATUS_URL = "http://sh2.ipyy.com/statusApi.aspx";
    private static final String DEFAULT_PASSWD_URL = "http://sh2.ipyy.com/pwd.aspx";

    private String smsUrl = DEFAULT_SMS_URL;
    private String mmsUrl = DEFAULT_MMS_URL;
    private String statusUrl = DEFAULT_STATUS_URL;
    private String passwordUrl = DEFAULT_PASSWD_URL;

    private String userId;
    private String account;
    private String password;
    private String signature;

    /**
     * send Short Message to given mobile
     *
     * @param mobile  target mobile
     * @param content message content
     */
    @Override
    public boolean sendSms(String mobile, String content) {
        return sendSms(mobile.split("\\s*,\\s*"), content);
    }

    /**
     * send Short Message to given mobile
     *
     * @param mobiles target mobiles
     * @param content message content
     */
    @Override
    public boolean sendSms(String[] mobiles, String content) {
        return sendSms(mobiles, content, 0);
    }

    /**
     * second delay to send Short Message to given mobiles
     *
     * @param mobiles target mobiles
     * @param content message content
     * @param second  duration
     */
    public boolean sendSms(String[] mobiles, String content, int second) {
        if (null == mobiles || mobiles.length < 1 || !StringUtils.hasText(content)) {
            throw new IllegalArgumentException();
        }

        Map<String, String> paramsMap = createParamsMap();
        String mobile = join(mobiles, MOBILE_SEPARATOR);
        String sendTime = second < 1 ? "" : formatDate(Dates.after(second, TimeUnit.SECONDS));

        content += signature;
        paramsMap.put("action", "send");       // 固定值
        paramsMap.put("mobile", mobile);       // 接收手机号码, "," 分隔
        paramsMap.put("content", content);     // 短信内容
        paramsMap.put("sendTime", sendTime);
        paramsMap.put("extno", "");            // 扩展子号, 不支持留空

        LOG.info("send sms: {}", paramsMap);
        post(smsUrl, paramsMap);

        return true;
    }

    /**
     * send Multi-Media Message
     * 该方法暂时没完成
     *
     * @param mobiles target mobiles
     * @param subject message subject
     * @param content message content
     */
    @SuppressWarnings("unused")
	public void sendMms(String[] mobiles, String subject, String content, int second) {
        if (true) {
            throw new UnsupportedOperationException();
        }
        // TODO content 需要 tms base64 编码

        if (null == mobiles || mobiles.length < 1 || !StringUtils.hasText(content)) {
            throw new IllegalArgumentException();
        }

        Map<String, String> paramsMap = createParamsMap();
        String mobile = join(mobiles, MOBILE_SEPARATOR);
        String sendTime = second < 1 ? "" : formatDate(Dates.after(second, TimeUnit.SECONDS));

        paramsMap.put("action", "send");       // 固定值
        paramsMap.put("mobile", mobile);       // 接收手机号码, "," 分隔
        paramsMap.put("subject", subject);     // 短信内容
        paramsMap.put("content", content);     // 短信内容
        paramsMap.put("sendTime", sendTime);
        paramsMap.put("extno", "");            // 扩展子号, 不支持留空

        LOG.info("send mms {}", paramsMap);
        post(smsUrl, paramsMap);
    }

    /**
     * query account overage
     *
     * @param type overage type, 0 = sms, other = mms
     */
    public void queryOverage(int type) {
        String url = type == 0 ? smsUrl : mmsUrl;

        Map<String, String> paramsMap = createParamsMap();
        paramsMap.put("action", "overage");

        LOG.info("query overage type: {}", 0 == type ? "SMS" : "MMS");
        post(url, paramsMap);
    }

    /**
     * 状态报告
     * 调用了没返回结果, 神马原因!!
     *
     * @param statusNo
     */
    public void queryStatus(String statusNo) {
        Map<String, String> paramsMap = createParamsMap();
        paramsMap.put("action", "query");
        if (StringUtils.hasText(statusNo)) {
            paramsMap.put("statusNum", statusNo);   // 必须为数字, 默认 4000, 可不填
        }

        post(statusUrl, paramsMap);
    }

    /**
     * modify password
     *
     * @param newPasswd new password
     */
    public void modifyPassword(String oldPasswd, String newPasswd) {
        if (null == oldPasswd || !oldPasswd.equals(password)) {
            // TODO
        }

        Map<String, String> paramsMap = createParamsMap();
        paramsMap.put("newpassword", newPasswd);

        throw new UnsupportedOperationException("");
        // post(passwordUrl, paramsMap);
    }

    protected Map<String, String> createParamsMap() {
        Map<String, String> params = Maps.newHashMap();
        params.put("userid", null != userId ? userId : "");         // 企业id, (不验证)
        params.put("account", null != account ? account : "");      // 用户帐户
        params.put("password", null != password ? password : "");   // 用户密码
        return params;
    }

    public static String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * @param array
     * @param sep
     * @return
     */
    protected static String join(Object[] array, String sep) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                buffer.append(sep);
            }
            buffer.append(array[i]);
        }
        return buffer.toString();
    }

    public static void post(String url, Map<String, String> data) {
        try {
            InputStream is = HttpUtils.post(url, data, 1000);
            String ret = IOUtils.toString(is, Charset.forName("UTF-8"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* ******************************
     *        getter setter
     * ******************************/

    public String getSmsUrl() {
        return smsUrl;
    }

    public void setSmsUrl(String smsUrl) {
        this.smsUrl = smsUrl;
    }

    public String getMmsUrl() {
        return mmsUrl;
    }

    public void setMmsUrl(String mmsUrl) {
        this.mmsUrl = mmsUrl;
    }

    public String getStatusUrl() {
        return statusUrl;
    }

    public void setStatusUrl(String statusUrl) {
        this.statusUrl = statusUrl;
    }

    public String getPasswordUrl() {
        return passwordUrl;
    }

    public void setPasswordUrl(String passwordUrl) {
        this.passwordUrl = passwordUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
