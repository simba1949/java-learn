package top.simba1949;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author SIMBA1949
 * @date 2019/5/26 11:08
 */
public class JavaUrl {
	public static void main(String[] args) throws MalformedURLException {
		URL url = new URL("https://image.baidu.com/search/index?tn=baiduimage&ipn=d&istype=2&ie=utf-8&oe=utf-8&word=%E7%AB%A5%E6%A2%A6#ref");
		System.out.println("获取协议名: " +url.getProtocol());
		System.out.println("获取域名或者IP: " + url.getHost());
		System.out.println("获取端口: " + url.getPort());
		System.out.println("请求资源带参数: " + url.getFile());
		System.out.println("请求资源不带参数: " + url.getPath());

		System.out.println("获取请求参数: " + url.getQuery());
		System.out.println("获取锚点: " + url.getRef());
	}
}
