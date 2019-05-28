package top.simba1949.socket.udp;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @author SIMBA1949
 * @date 2019/5/26 12:09
 */
public class BaseTypeUDPClient {
	public static void main(String[] args) throws IOException {
		// 1. 创建本机的 DatagramSocket，指定地址（ip 和 端口）
		InetSocketAddress localhost = new InetSocketAddress("localhost", 9999);
		DatagramSocket datagramSocket = new DatagramSocket(localhost);
		// 2.需要容器来接收 DatagramPacket 包裹的数据
		byte[] container = new byte[1024 * 60];
		DatagramPacket datagramPacket = new DatagramPacket(container, 0, container.length);
		// 3.接收数据
		datagramSocket.receive(datagramPacket);
		// 4。分析数据
		byte[] data = datagramPacket.getData();
		int length = datagramPacket.getLength();

		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);
		int i = dis.readInt();
		System.out.println("客户端接收端基本类型数据为： " + i);

		// 释放资源
		bais.close();
		dis.close();
		datagramSocket.close();
	}
}
