package top.simba1949.socket.udp;

import java.io.*;
import java.net.*;

/**
 * @author SIMBA1949
 * @date 2019/5/26 12:36
 */
public class FileUDPServer {
	public static void main(String[] args) throws IOException {
		// 1. 创建发送端 DatagramSocket对象，指定本机地址
		SocketAddress socketAddress = new InetSocketAddress("localhost", 8888);
		DatagramSocket datagramSocket = new DatagramSocket(socketAddress);
		// 2.装备数据，将数据转换成字节数组
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		File file = new File("T:/IDE/IDEA/Workspace/java-learn/java-socket/src/main/resources/udp/udpInput.jpg");
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		byte[] flush = new byte[1024 * 8];
		int len;
		while ((len = bis.read(flush)) != -1){
			baos.write(flush, 0, len);
		}
		byte[] dataBytes = baos.toByteArray();
		baos.close();
		bis.close();
		// 3.创建 DatagramPacket 并指定发送目的地
		SocketAddress destAddress = new InetSocketAddress("localhost", 9999);
		DatagramPacket datagramPacket = new DatagramPacket(dataBytes, 0, dataBytes.length, destAddress);
		// 4.发送包裹
		datagramSocket.send(datagramPacket);
		// 5.关闭资源
		datagramSocket.close();

	}
}
