package com.yd.dby.a.sys.controller;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Producer;
import com.yd.dby.utils.uuid.YdUtilUUID;

@Controller
@RequestMapping(value = "/")
public class YdSysControllerCommon {
	
	@Autowired
	private Producer captchaProducer;
	
	@Autowired
	private StringRedisTemplate redis;
	
	/**
	 * 生成验证码
	 */
	@RequestMapping(value = "/captcha-image")  
    public ModelAndView getKaptchaImage(HttpServletRequest request,  
            HttpServletResponse response) throws Exception { 
        response.setDateHeader("Expires", 0);  
        response.setHeader("Cache-Control",  
                "no-store, no-cache, must-revalidate");  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        response.setHeader("Pragma", "no-cache");  
        response.setContentType("image/jpeg");
  
        String capText = captchaProducer.createText();
        try {
        	String uuid = YdUtilUUID.generate();
        	redis.opsForValue().set(uuid, capText, 60*5, TimeUnit.SECONDS);
        	Cookie cookie = new Cookie("ydCaptchaCode", uuid);
        	response.addCookie(cookie);
		} catch (Exception e) {
			e.printStackTrace();
		}

        BufferedImage bi = captchaProducer.createImage(capText);  
        ServletOutputStream out = response.getOutputStream();  
        // write the data out  
        ImageIO.write(bi, "jpg", out);  
        try {
            out.flush();  
        } finally {
            out.close();  
        }
        return null;  
    }
	
}
