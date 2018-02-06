package com.blurdancer.domain.util;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * The type J push util.
 */
public class JPushUtil {


    private final static String appKey = "0254518a604e503e8d";

    private final static String masterSecret = "26af91be3e2fcc4";

    private final static Integer timeToLive = 60 * 60 * 24;

    static Logger logger = Logger.getLogger(JPushUtil.class);

    static Boolean ENV = null;

    private static JPushClient getClient() {
        if (ENV == null) {
            ResourceBundle resource = ResourceBundle.getBundle("application");
            String arg = resource.getString("spring.profiles.active");
            if ("dev".equals(arg)) {
                ENV = false;
            } else {
                ENV = true;
            }
        }
        return new JPushClient(masterSecret, appKey);
    }


    /**
     * 推送所有不含附加信息
     *
     * @Author Tpf
     * @Date 2017 -06-21 11:12:56
     */
    public static boolean sendAllWithOutExtras(String content) {
        JPushClient jPushClient = getClient();

        boolean flag = false;
        try {
            PushPayload payload = JPushUtil.buildPushObject_all_alert_with_out_extras(content);
            PushResult result = jPushClient.sendPush(payload);
            if (null != result) {
                logger.info("Get resul ---" + result);
                flag = true;
            }
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
            flag = false;
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
            flag = false;
        } finally {
            if (jPushClient != null) {
                jPushClient.close();
            }
        }
        return flag;
    }

    /**
     * 推送所有,携带附加信息
     *
     * @Author Tpf
     * @Date 2017 -06-21 11:12:56
     */
    public static boolean sendPushAll(Integer id, Integer type, String title, String content) {
        JPushClient jPushClient = getClient();
        boolean flag = false;
        try {
            PushPayload payload = buildPushObject_all_alert(id, type, content);
            PushResult result = jPushClient.sendPush(payload);
            if (null != result) {
                logger.info("Get resul ---" + result);
                flag = true;
            }
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
            flag = false;
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
            flag = false;
        } finally {
            if (jPushClient != null) {
                jPushClient.close();
            }
        }
        return flag;
    }

    /**
     * 根据用户别名推送,带附加信息
     *
     * @param alias the alias
     * @return the boolean
     * @Author Tpf
     * @Date 2017 -06-21 11:12:56
     */
    public static boolean sendPushOneAliasWithExtras(String content, String alias, Integer id, Integer type) {
        JPushClient jPushClient = getClient();
        HashMap map = getMap(id, type);
        boolean flag = false;
        try {
            PushPayload payload = JPushUtil.buildPushObject_oneAlias_alert_with_extras(alias, content, map);
            PushResult result = jPushClient.sendPush(payload);
            if (null != result) {
                logger.info("Get resul ---" + result);
                flag = true;
            }
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
            flag = false;
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
            flag = false;
        } finally {
            if (jPushClient != null) {
                jPushClient.close();
            }
        }
        return flag;
    }

    //根据用户别名推送通知,不带附加信息
    public static Boolean sendPushOneAliasWithOutExtras(String content, String alias) {
        JPushClient jPushClient = null;
        Boolean flag = true;
        try {
            jPushClient = getClient();
            PushPayload payload = JPushUtil.buildPushObject_oneAlias_alert(alias, content);
            PushResult result = jPushClient.sendPush(payload);
            if (null != result) {
                logger.info("Get resul ---" + result);
            }
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
            flag = false;
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
        } finally {
            if (jPushClient != null) {
                jPushClient.close();
            }
        }
        return flag;
    }


    /**
     * 推送所有安卓,带附加信息
     *
     * @param title the title
     * @return the boolean
     * @Author Tpf
     * @Date 2017 -06-21 11:12:56
     */
    public static boolean sendPushAllAndroid(Integer id, Integer type, String title, String content) {
        JPushClient jPushClient = getClient();
        HashMap map = getMap(id, type);
        boolean flag = false;
        try {
            PushPayload payload = buildPushObject_all_android_alert(title, content, map);
            PushResult result = jPushClient.sendPush(payload);
            if (null != result) {
                logger.info("Get resul ---" + result);
                flag = true;
            }
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
            flag = false;
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
            flag = false;
        } finally {
            if (jPushClient != null) {
                jPushClient.close();
            }
        }
        return flag;
    }

    /**
     * 推送所有安卓,不带附加信息
     *
     * @Author Tpf
     * @Date 2017 -06-21 11:12:56
     */
    public static boolean sendPushAllAndroidWithOutExtras(String content) {
        JPushClient jPushClient = getClient();
        boolean flag = false;
        try {
            PushPayload payload = buildPushObject_all_android_alert_with_out_extras(content);
            PushResult result = jPushClient.sendPush(payload);
            if (null != result) {
                logger.info("Get resul ---" + result);
                flag = true;
            }
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
            flag = false;
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
            flag = false;
        } finally {
            if (jPushClient != null) {
                jPushClient.close();
            }
        }
        return flag;
    }

    /**
     * 推送所有iOS,带附加信息
     *
     * @param content the title
     * @return the boolean
     * @Author Tpf
     * @Date 2017 -06-21 11:12:56
     */
    public static boolean sendPushAllIOS(Integer id, Integer type, String content) {
        JPushClient jPushClient = getClient();
        HashMap map = getMap(id, type);
        boolean flag = false;
        try {
            PushPayload payload = buildPushObject_all_ios_alert(content, map);
            PushResult result = jPushClient.sendPush(payload);
            if (null != result) {
                logger.info("Get resul ---" + result);
                flag = true;
            }
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
            flag = false;
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
            flag = false;
        } finally {
            if (jPushClient != null) {
                jPushClient.close();
            }
        }
        return flag;
    }

    /**
     * 推送所有iOS,不带附加信息
     *
     * @param content the title
     * @return the boolean
     * @Author Tpf
     * @Date 2017 -06-21 11:12:56
     */
    public static boolean sendPushAllIOSWithOutExtras(String content) {
        JPushClient jPushClient = getClient();
        boolean flag = false;
        try {
            PushPayload payload = buildPushObject_all_ios_alert_with_out_extras(content);
            PushResult result = jPushClient.sendPush(payload);
            if (null != result) {
                logger.info("Get resul ---" + result);
                flag = true;
            }
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
            flag = false;
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
            flag = false;
        } finally {
            if (jPushClient != null) {
                jPushClient.close();
            }
        }
        return flag;
    }


    /**
     * 推送所有,包含附加信息
     *
     * @Author Tpf
     * @Date 2017 -06-21 11:12:56
     */
    private static PushPayload buildPushObject_all_alert(Integer id, Integer type, String content) {
        HashMap map = getMap(id, type);
        return PushPayload
                .newBuilder()
                .setPlatform(Platform.all())//设置接受的平台
                .setOptions(Options.newBuilder().setApnsProduction(ENV).build())//IOS推送生产环境
                .setAudience(Audience.all())//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
                .setNotification(Notification.newBuilder()
                        .setAlert(content)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(content)
                                .addExtras(map)
                                .build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .addExtras(map)
                                .build())
                        .build())
                .build();
    }

    /**
     * 推送所有,不含附加信息
     *
     * @Author Tpf
     * @Date 2017 -06-21 11:12:56
     */
    private static PushPayload buildPushObject_all_alert_with_out_extras(String content) {
        return PushPayload
                .newBuilder()
                .setPlatform(Platform.all())//设置接受的平台
                .setOptions(Options.newBuilder().setApnsProduction(ENV).build())//推送环境
                .setAudience(Audience.all())//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
                .setNotification(Notification.newBuilder()
                        .setAlert(content)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(content)
                                .build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .build())
                        .build())
                .build();
    }


    //根据别名推送,不带附加信息
    private static PushPayload buildPushObject_oneAlias_alert(String alias, String title) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setOptions(Options.newBuilder().setApnsProduction(ENV).build())//IOS推送生产环境
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(title))
                .build();
    }


    //根据别名推送单个,包含附加信息
    private static PushPayload buildPushObject_oneAlias_alert_with_extras(String alias, String content, Map extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setOptions(Options.newBuilder().setApnsProduction(ENV).build())//IOS推送生产环境
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                        .setAlert(content)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(content)
                                .addExtras(extras)
                                .build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .addExtras(extras)
                                .build())
                        .build())
                .build();
    }


    /**
     * 根据uid推送通知
     */
    private static PushPayload buildPushObject_registrationId_alert(String alias, String title, String content, HashMap map) {
        return PushPayload
                .newBuilder()
                .setPlatform(Platform.all())
                .setOptions(Options.newBuilder().setApnsProduction(ENV).build())//IOS推送开发环境
                .setAudience(Audience.registrationId(alias))
                .setNotification(Notification.newBuilder()
                        .setAlert(content)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(content)
                                .addExtras(map)
                                .build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .addExtras(map)
                                .build())
                        .build())
                .build();
    }

    //推送所有的android平台带附加信息
    private static PushPayload buildPushObject_all_android_alert(String title, String content, HashMap map) {
        return PushPayload
                .newBuilder()
                .setPlatform(Platform.android())//设置接受的平台
                .setOptions(Options.newBuilder().setApnsProduction(ENV).build())//IOS推送开发环境
                .setAudience(Audience.all())//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
                .setNotification(Notification.android(content, title, map))
                .build();
    }

    //推送所有的android平台不带附加信息
    private static PushPayload buildPushObject_all_android_alert_with_out_extras(String content) {
        return PushPayload
                .newBuilder()
                .setPlatform(Platform.android())//设置接受的平台
                .setOptions(Options.newBuilder().setApnsProduction(ENV).build())//IOS推送开发环境
                .setAudience(Audience.all())//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
                .setNotification(Notification.alert(content))
                .build();
    }


    //推送所有的ios平台
    private static PushPayload buildPushObject_all_ios_alert(String content, HashMap map) {
        return PushPayload
                .newBuilder()
                .setPlatform(Platform.ios())//设置接受的平台
                .setOptions(Options.newBuilder().setApnsProduction(ENV).build())//IOS推送开发环境
                .setAudience(Audience.all())//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
                .setNotification(Notification.ios(content, map))
                .build();
    }

    //推送所有的ios平台不带附加信息
    private static PushPayload buildPushObject_all_ios_alert_with_out_extras(String content) {
        return PushPayload
                .newBuilder()
                .setPlatform(Platform.ios())//设置接受的平台
                .setOptions(Options.newBuilder().setApnsProduction(ENV).build())//IOS推送开发环境
                .setAudience(Audience.all())//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
                .setNotification(Notification.alert(content))
                .build();
    }


    /**
     * 根据uid推送message
     *
     * @param alias   the alias
     * @param content the content
     * @return the push payload
     * @Author Tpf
     * @Date 2017 -06-26 11:45:01
     */
    private static PushPayload buildPushObject_registrationId_message(String alias, String content) {
        Message.Builder message = new Message.Builder();
        message.setTitle(content);
        message.setMsgContent(content);
        message.addExtra("open", "opendancebaby://video?id=666");
        return PushPayload
                .newBuilder()
                .setPlatform(Platform.all())
                .setOptions(Options.newBuilder().setApnsProduction(ENV).build())//IOS推送环境
                .setAudience(Audience.alias(alias))
                .setMessage(message.build())
                .build();
    }


    private static HashMap getMap(Integer meidaId, Integer type) {
        HashMap map = new HashMap();
        switch (type) {
            case 1: {
                map.put("open", "opendancebaby://video?id=" + meidaId);
            }
            break;
            case 2: {
                map.put("open", "opendancebaby://audio?id=" + meidaId);
            }
            break;
            case 3: {
                map.put("open", "opendancebaby://news?id=" + meidaId);
            }
            break;
            case 4: {
                map.put("open", "opendancebaby://photo?id=" + meidaId);
            }
            break;
            case 5: {
                map.put("open", "opendancebaby://commpetition?id=" + meidaId);
            }
            break;
            case 6: {
                map.put("open", "opendancebaby://album?id=" + meidaId);
            }
            break;
            case 7: {
                map.put("open", "opendancebaby://videoLive?id=" + meidaId);
            }
            break;
            case 8: {
                map.put("open", "opendancebaby://userTrend?id=" + meidaId);
            }
            break;
            default:
                break;
        }
        return map;
    }
//    //最新版3.0的极光推送
//    public static PushPayload buildPushIOS(Integer id, Integer type, String content) {
//        HashMap map = getMap(id, type);
//        return PushPayload
//                .newBuilder()
//                .setPlatform(Platform.all())//设置接受的平台
//                .setOptions(Options.newBuilder().setApnsProduction(ENV).build())//IOS推送生产环境
//                .setAudience(Audience.all())//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
//                .setNotification(Notification.newBuilder()
//                        .setAlert(content)
//                        .addPlatformNotification(AndroidNotification.newBuilder()
//                                .setTitle(content)
//                                .addExtras(map)
//                                .build())
//                        .addPlatformNotification(IosNotification.newBuilder()
//                                .addExtras(map)
//                                .build())
//                        .build())
//                .build();
//    }

    /**
     * 根据用户id推送
     *
     * @param title the title
     * @param uid   the uid
     * @return the boolean
     * @Author Tpf
     * @Date 2017 -06-21 11:12:56
     */
    public static boolean sendPushOneRegistrationId(String title, String content, String uid, Integer id, Integer type) {
        JPushClient jPushClient = getClient();
        HashMap map = getMap(id, type);
        boolean flag = false;
        try {
            PushPayload payload = JPushUtil.buildPushObject_registrationId_alert(uid, title, content, map);
            PushResult result = jPushClient.sendPush(payload);
            if (null != result) {
                logger.info("Get resul ---" + result);
                flag = true;
            }
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
            flag = false;
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
            flag = false;
        } finally {
            if (jPushClient != null) {
                jPushClient.close();
            }
        }
        return flag;
    }

}

