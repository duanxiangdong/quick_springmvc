package com.duan.sysmodule.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class XmlUtils {
	private static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	
	private XmlUtils(){}
	
	public static String beanToXml(Object bean){
		if(null == bean){
			return null;
		}else{
			XStream xstream = new XStream(new XppDriver(new NoNameCoder()));
			xstream.autodetectAnnotations(true);
			xstream.aliasSystemAttribute(null, "class"); // 去掉 class 属性
			return XML_HEAD + xstream.toXML(bean);
		}
	}
	
	/**
	 * XML转换为Bean
	 * @param classMap
	 * @param xml
	 * @return
	 */
	public static Object xmlToBean(Map<String, Class<?>> classMap, String xml) {
		if(null == classMap || classMap.isEmpty() || null == xml){
			return null;
		}else{
			XStream xstream = new XStream(new XppDriver(new NoNameCoder()));
			xstream.autodetectAnnotations(true);
			for (Iterator<Map.Entry<String, Class<?>>> it = classMap.entrySet().iterator(); it.hasNext();) {
	            Map.Entry<String, Class<?>> m = (Map.Entry<String, Class<?>>) it.next();
	            xstream.alias(m.getKey(), m.getValue());
	        }
	        return xstream.fromXML(xml);
		}
    }
	
	/**
	 * 格式化XML输出(dom4J版本1.6以上)
	 * @param str  未经格式化的XML
	 * @return     输出格式化后的XML
	 */
	public static String formatXml(String str) {
		if(StringUtils.isNull(str)){
			return null;
		}
		StringReader in = null;
		StringWriter out = null;
		try {
			SAXReader reader = new SAXReader();
			//创建一个串的字符输入流
			in = new StringReader(str);
			Document doc = reader.read(in);
			//创建输出格式
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setNewLineAfterDeclaration(false);
			//创建输出
			out = new StringWriter();
			//创建输出流
			XMLWriter writer = new XMLWriter(out, format);
			//输出格式化的串到目标中,格式化后的串保存在out中
			writer.write(doc);
		} catch (IOException io) {
			System.out.println("对xml字符串进行格式化时产生IOException异常：" + io.getMessage());
		} catch (DocumentException de) {
			System.out.println("对xml字符串进行格式化时产生DocumentException异常：" + de.getMessage());
		} finally {
			quietClose(in);
			quietClose(out);
		}
		return out.toString();
	}
    
	/**
	 * 关闭读取流
	 * @param reader
	 */
	private static void quietClose(Reader reader) {
		try {
			if (reader != null) {
				reader.close();
			}
		} catch (IOException io) {
			System.out.println("关闭Reader时出现异常：" + io.getMessage());
		}
	}

	/**
	 * 关闭输出流
	 * @param writer
	 */
	private static void quietClose(Writer writer) {
		try {
			if (writer != null) {
				writer.close();
			}
		} catch (IOException io) {
			System.out.println("关闭Writer时出现异常：" + io.getMessage());
		}
	}
}