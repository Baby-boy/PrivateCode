package com.glanway.ctrlhr.util.sms;

/**
 */
public interface SmsSender {

    boolean sendSms(String mobile, String content);

    boolean sendSms(String[] mobiles, String content);

}
