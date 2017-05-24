package com.glanway.iclock.servlet.jsp.tags;



import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.glanway.iclock.servlet.jsp.EscapeXmlELResolver;
import com.glanway.iclock.util.HtmlCleaner;

import java.io.IOException;

/**
 * @author vacoor
 */
public class SafeHtmlTag extends BodyTagSupport {
    /**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -6067618068456027767L;
	private String whitelist;

    @Override
    public int doStartTag() throws JspException {
        pageContext.setAttribute(EscapeXmlELResolver.ESCAPE_XML_ATTRIBUTE, false);
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            String content = getBodyContent().getString();

            if ("none".equals(whitelist)) {
                content = HtmlCleaner.none(content);
            } else if ("basic".equals(whitelist)) {
                content = HtmlCleaner.basic(content);
            } else if ("basic-with-images".equals(whitelist)) {
                content = HtmlCleaner.basicWithImages(content);
            } else if ("simple-text".equals(whitelist)) {
                content = HtmlCleaner.simpleText(content);
            } else if (null == whitelist || "relaxed".equals(whitelist)) {
                content = HtmlCleaner.relaxed(content);
            } else {
                throw new JspTagException("invalid whitelist: " + whitelist + ", whitelist allowed value: none, basic, basic-with-images, simple-text, relaxed");
            }

            pageContext.getOut().write(content);
            return super.doEndTag();
        } catch (IOException e) {
            throw new JspException(e);
        } finally {
            pageContext.removeAttribute(EscapeXmlELResolver.ESCAPE_XML_ATTRIBUTE);
        }
    }

    @Override
    public void release() {
        whitelist = null;
        super.release();
    }

    public String getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(String whitelist) {
        this.whitelist = whitelist;
    }
}
