package top.simba1949.ip;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;

/**
 * @author SIMBA1949
 * @date 2019/5/26 10:11
 */
public class JavaIp {
	public static void main(String[] args) throws UnknownHostException, UnsupportedEncodingException {
		getLocalHostInfo();
//		createInetAddressObj();
	}

	public static void getLocalHostInfo() throws UnknownHostException {
		// 通过 InetAddress.getLocalHost() 创建本机的 InetAddress 对象
		InetAddress localHost = InetAddress.getLocalHost();
		System.out.println("localHost: " + localHost);

		System.out.println("Address: " + localHost.getAddress());
		System.out.println("CanonicalHostName: " + localHost.getCanonicalHostName());
		System.out.println("HostAddress: " + localHost.getHostAddress());
		System.out.println("HostName: " + localHost.getHostName());

		// 通过 ip，port 创建
		SocketAddress sa1 = new InetSocketAddress("127.0.0.1", 80);
		// 通过 域名，port 创建
		SocketAddress sa2 = new InetSocketAddress("www.baidu.com", 80);
		System.out.println(sa1);
		System.out.println(sa2);
	}

	public static void createInetAddressObj() throws UnknownHostException {
		InetAddress inetAddress = InetAddress.getByName("www.baidu.com");

		// 通过 ip，port 创建
		SocketAddress sa1 = new InetSocketAddress("127.0.0.1", 80);
		// 通过 域名，port 创建
		SocketAddress sa2 = new InetSocketAddress("www.baidu.com", 80);
		System.out.println(sa1);
		System.out.println(sa2);
	}
}
