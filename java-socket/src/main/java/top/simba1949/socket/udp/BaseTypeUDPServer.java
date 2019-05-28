package top.simba1949.socket.udp;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @author SIMBA1949
 * @date 2019/5/26 12:09
 */
public class BaseTypeUDPServer {
	public static void main(String[] args) throws IOException {
		// 1. 创建一个 DatagramSocket ，并指定本机的地址和端口
		InetSocketAddress localhost = new InetSocketAddress("127.0.0.1", 8888);
		DatagramSocket datagramSocket = new DatagramSocket(localhost);
		// 2.准备数据，并转换成字节数组
		int data = 9;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeInt(data);
		byte[] dataBytes = baos.toByteArray();
		// 3.将字节数组封装到 DatagramPacket 中，并指定要要发送的地址和端口
		InetSocketAddress sendAddress = new InetSocketAddress("127.0.0.1", 9999);
		DatagramPacket datagramPacket = new DatagramPacket(dataBytes, 0, dataBytes.length, sendAddress);
		// 4.发送数据
		datagramSocket.send(datagramPacket);
		// 5.关闭资源
		baos.close();
		dos.close();
		datagramSocket.close();
	}
}
