package top.simba1949.socket.udp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;

/**
 * @author SIMBA1949
 * @date 2019/5/26 12:36
 */
public class FileUDPClient {
	public static void main(String[] args) throws IOException {
		// 1.创建本机 DatagramSocket 对象
		SocketAddress localAddress = new InetSocketAddress("localhost", 9999);
		DatagramSocket datagramSocket = new DatagramSocket(localAddress);
		// 2.创建一个容器，用于接收数据，容器大小不要超过 1024 * 60
		byte[] container = new byte[1024 * 60];
		// 3.创建接收包裹
		DatagramPacket datagramPacket = new DatagramPacket(container, 0, container.length);
		// 4.接收数据，并分析
		datagramSocket.receive(datagramPacket);
		byte[] data = datagramPacket.getData();
		int length = datagramPacket.getLength();
		File file = new File("T:/IDE/IDEA/Workspace/java-learn/java-socket/src/main/resources/udp/udpOutput.jpg");
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		bos.write(data, 0, length);
		// 5.释放资源
		bos.close();
		datagramSocket.close();

	}
}
