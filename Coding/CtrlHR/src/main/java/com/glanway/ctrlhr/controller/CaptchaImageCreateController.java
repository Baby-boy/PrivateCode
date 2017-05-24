package com.glanway.ctrlhr.controller;
/*
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.awt.image.BufferedImage;
import java.util.Date;

@Controller
@RequestMapping("captcha")
public class CaptchaImageCreateController {
	
	/* @Autowired
    private Producer captchaProducer = null;

    @Autowired
    public void setCaptchaProducer(Producer captchaProducer) {
        this.captchaProducer = captchaProducer;
    }

    @RequestMapping("captchaImage")
	public ModelAndView handleRequest(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/jpeg");
		// create the text for the image
		String capText = captchaProducer.createText();
		// store the text in the session
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_DATE, new Date().getTime());
		// create the image with the text
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
    @ResponseBody
	@RequestMapping("validateSigninCaptcha")
	public Boolean validateCaptcha(HttpSession session, String captcha) {
		String id = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(id.equals(captcha)){
			return true;
		}else{
			return false;
		}
	}
*/
}