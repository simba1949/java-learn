package top.simba1949.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 接收端
 * 1. 使用 DatagramSocket 指定端口，创建接收端
 * 2. 准备容器，封装成 DatagramPacket 包裹
 * 3. 阻塞式接受包裹 receiver(DatagramPacket dp)
 * 4. 分析数据
 *     byte[] getData()
 *     getLength()
 * 5. 释放资源
 *
 * @author SIMBA1949
 * @date 2019/5/26 11:42
 */
public class UDPClient {
	public static void main(String[] args) throws IOException {
		// 1. 使用 DatagramSocket 指定端口，创建接收端
		DatagramSocket datagramSocket = new DatagramSocket(9999);
		// 2. 准备容器，封装成 DatagramPacket 包裹，容器建议最多 1024 * 60
		byte[] container  = new byte[1024 * 60];
		DatagramPacket datagramPacket = new DatagramPacket(container, 0, container.length);
		// 3. 阻塞式接受包裹 receiver(DatagramPacket dp)
		datagramSocket.receive(datagramPacket);
		// 4. 获取真实数据，并分析数据
		byte[] receiver = datagramPacket.getData();
		int length = datagramPacket.getLength();
		System.out.println(new String(receiver, 0, length));
		// 5. 释放资源
		datagramSocket.close();
	}
}
