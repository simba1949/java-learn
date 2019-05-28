package top.simba1949.socket.udp.interactive;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 实现在线聊天——接收功能
 *
 * @author SIMBA1949
 * @date 2019/5/26 13:11
 */
public class TalkReceive implements Runnable{
	private DatagramSocket datagramSocket;
	private int port;
	String fromName;

	public TalkReceive(int port, String fromName){
		this.port = port;
		this.fromName = fromName;
		try {
			// 1. 使用 DatagramSocket 指定端口，创建接收端
			datagramSocket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (true){
				// 2. 准备容器，封装成 DatagramPacket 包裹，容器建议最多 1024 * 60
				byte[] container  = new byte[1024 * 60];
				DatagramPacket datagramPacket = new DatagramPacket(container, 0, container.length);
				// 3. 阻塞式接受包裹 receiver(DatagramPacket dp)
				datagramSocket.receive(datagramPacket);
				// 4. 获取真实数据，并分析数据
				byte[] receiver = datagramPacket.getData();
				int length = datagramPacket.getLength();
				System.out.println(fromName + " : " +new String(receiver, 0, length));
				if ("bye".equals(receiver)){
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 5. 释放资源
			datagramSocket.close();
		}

	}
}
