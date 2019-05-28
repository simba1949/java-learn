package top.simba1949.socket.udp;

import java.io.IOException;
import java.net.*;

/**
 * 发送端
 * 1. 使用 DatagramSocket 指定端口，创建发送端
 * 2. 准备数据，一定要转换成**字节数组**
 * 3. 封装成 DatagramPacket 包裹，需要指定目的地
 * 4. 发送包裹 send(DatagramPacket dp)
 * 5. 释放资源
 *
 * @author SIMBA1949
 * @date 2019/5/26 11:42
 */
public class UDPServer {
	public static void main(String[] args) throws IOException {
		System.out.println("发送端启动中。。。。");
		//1. 使用 DatagramSocket 指定端口，创建发送端（ip和port）
		DatagramSocket datagramSocket = new DatagramSocket(8888);
		// 2. 准备数据，一定要转换成**字节数组**
		String data = "天道酬勤";
		byte[] bytes = data.getBytes();
		// 3. 封装成 DatagramPacket 包裹，需要指定目的地（ip 和 port）
		InetSocketAddress destAdd = new InetSocketAddress("localhost", 9999);
		DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length, destAdd);
		// 4. 发送包裹 send(DatagramPacket dp)
		datagramSocket.send(datagramPacket);
		//  5. 释放资源
		datagramSocket.close();
	}
}
