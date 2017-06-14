package com.glanway.hr.app.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by tianxuan
 * date: 2015/11/26
 * describe:
 */
public class HttpUtils {

    private static Log log = LogFactory.getLog(HttpUtils.class);

    /**
     * 定义编码格式 UTF-8
     */
    public static final String POST_METHOD                 = "POST";
    public static final String ENCODING                    = "UTF-8";
    /**
     * 定义编码格式 GBK
     */
    public static final String URL_PARAM_DECODECHARSET_GBK = "GBK";

    public static final String EMPTY = "";

    private static final String URL_PARAM_CONNECT_FLAG = "&";

    private static MultiThreadedHttpConnectionManager connectionManager = null;

    private static int connectionTimeOut = 25000;

    private static int socketTimeOut = 25000;

    private static int maxConnectionPerHost = 20;

    private static int maxTotalConnections = 20;

    private static HttpClient client;

    static {
        connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(connectionTimeOut);
        connectionManager.getParams().setSoTimeout(socketTimeOut);
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(maxConnectionPerHost);
        connectionManager.getParams().setMaxTotalConnections(maxTotalConnections);
        client = new HttpClient(connectionManager);
    }

    /**
     * POST方式提交数据
     * @param url
     *          待请求的URL
     * @param paramsMap
     *          要提交的数据
     * @param enc
     *          编码
     * @return
     *          响应结果
     * @throws IOException
     *          IO异常
     */
    /*public static String URLPost(String url, Map<String, String> paramsMap, String enc){
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        enc = null != enc ? enc : ENCODING;
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, enc));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }*/

    /**
     * GET方式提交数据
     * @param url
     *          待请求的URL
     * @param params
     *          要提交的数据
     * @param enc
     *          编码
     * @return
     *          响应结果
     * @throws IOException
     *          IO异常
     */
    public static String URLGet(String url, Map<String, String> params, String enc) {

        StringBuffer response = new StringBuffer();
        GetMethod getMethod = null;
        String strtTotalURL = URLAppend(url, params, enc);
        log.debug("GET请求URL = \n" + strtTotalURL);

        try {
            getMethod = new GetMethod(strtTotalURL);
            getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + enc);
            //执行getMethod
            int statusCode = client.executeMethod(getMethod);
            if (statusCode == HttpStatus.SC_OK) {
                InputStream inputStream = getMethod.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String str = EMPTY;
                while ((str = br.readLine()) != null) {
                    response.append(str);
                }
            } else {
                log.debug("响应状态码 = " + getMethod.getStatusCode());
            }
        } catch (HttpException e) {
            log.error("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
            e.printStackTrace();
        } catch (IOException e) {
            log.error("发生网络异常", e);
            e.printStackTrace();
        } finally {
            if (getMethod != null) {
                getMethod.releaseConnection();
                getMethod = null;
            }
        }
        return response.toString();
    }

    /**
     * 据Map生成URL字符串
     * @param map
     *          Map
     * @param valueEnc
     *          URL编码
     * @return
     *          URL
     */
    public static String getUrl(Map<String, String> map, String valueEnc) {

        if (null == map || map.keySet().size() == 0) {
            return (EMPTY);
        }
        StringBuffer url = new StringBuffer();
        Set<String> keys = map.keySet();
        for (Iterator<String> it = keys.iterator(); it.hasNext();) {
            String key = it.next();
            if (map.containsKey(key)) {
                String val = map.get(key);
                String str = val != null ? val : EMPTY;
                try {
                    if (null != valueEnc && !EMPTY.equals(valueEnc))
                        str = URLEncoder.encode(str, valueEnc);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                url.append(key).append("=").append(str).append(URL_PARAM_CONNECT_FLAG);
            }
        }
        String strURL = EMPTY;
        strURL = url.toString();
        if (URL_PARAM_CONNECT_FLAG.equals(EMPTY + strURL.charAt(strURL.length() - 1))) {
            strURL = strURL.substring(0, strURL.length() - 1);
        }

        return (strURL);
    }

    public static String URLAppend(String url, Map<String, String> params, String enc) {
        StringBuffer strtTotalURL = new StringBuffer(url);

        if (strtTotalURL.indexOf("?") == -1) {
            strtTotalURL.append("?").append(getUrl(params, enc));

        } else {
            strtTotalURL.append("&").append(getUrl(params, enc));
        }
        return strtTotalURL.toString();
    }

    public static boolean postOnly(String serverUrl, Map<String, String> data, int timeout) {
        InputStream is = null;
        try {
            is = post(serverUrl, data, timeout);
            return true;
        } catch (Throwable ignore) {
            // ignore
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
        return false;
    }

    /**
     *
     * @param serverUrl
     * @param data
     * @param timeout
     * @return
     * @throws IOException
     */
    public static InputStream post(String serverUrl, Map<String, String> data, int timeout) throws IOException {
        OutputStreamWriter writer = null;
        URL url;

        try {
            url = new URL(serverUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if ("https".equals(url.getProtocol())) {
                log.debug("init ssl context for {}");
                // 初始化 SSL 上下文
                SSLContext sslContext = SSLContext.getInstance("SSL");
                // 类似12306 会使用证书, 这里初始化为不是用密钥库, 信任任何证书
                sslContext.init(new KeyManager[] {}, new TrustManager[] { new TrustAnyTrustManager() },
                    new SecureRandom());
                SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

                ((HttpsURLConnection) conn).setSSLSocketFactory(sslSocketFactory);
            }

            conn.setRequestMethod(POST_METHOD);
            conn.setDoOutput(true);
            conn.setConnectTimeout(timeout);
            writer = new OutputStreamWriter(conn.getOutputStream());

            if (null != data) {
                boolean first = true;
                for (Map.Entry<String, String> entry : data.entrySet()) {
                    if (!first) {
                        writer.write('&');
                    }
                    first = false;
                    writer.write(URLEncoder.encode(entry.getKey(), ENCODING));
                    writer.write('=');
                    writer.write(URLEncoder.encode(entry.getValue(), ENCODING));
                }
            }
            writer.flush();

            return conn.getInputStream();
        } catch (NoSuchAlgorithmException e) {
            log.warn("", e);

            // return rethrowRuntimeException(e);
            return null;
        } catch (KeyManagementException e) {
            log.warn("", e);
            //return rethrowRuntimeException(e);
            return null;
        } finally {
            if (null != writer) {
                try {
                    writer.close();
                } catch (IOException ignore) {
                    /* ignore */}
            }
        }
    }

    /**
     * 信任任何证书的信任库管理器
     */
    private static class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }

    private HttpUtils() {
    }
}
