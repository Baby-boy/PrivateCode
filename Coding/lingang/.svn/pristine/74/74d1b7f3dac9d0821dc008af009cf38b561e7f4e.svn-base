package com.lingang.common.custom;

import java.io.File;

import org.apache.log4j.DailyRollingFileAppender;

public class DailyRollingFileAppenderCustom extends DailyRollingFileAppender {
	@Override
    public void setFile(String file) {
        String filePath = file;
        File fileCheck = new File(filePath);
        if (!fileCheck.exists())
            fileCheck.getParentFile().mkdirs();
        super.setFile(filePath);
    }
}
