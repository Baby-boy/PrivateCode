package com.yd.dby.utils.xml;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class YdUtilXml {

	public static String getText(String xmlstr) throws Exception {
		Document doc = DocumentHelper.parseText(xmlstr);
		Element el = doc.getRootElement();
		return el.getText();
	}

}