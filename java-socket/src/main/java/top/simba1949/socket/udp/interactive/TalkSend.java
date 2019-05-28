package top.simba1949.socket.udp.interactive;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * 实现在线聊天——发送功能
 *
 * @author SIMBA1949
 * @date 2019/5/26 13:10
 */
public class TalkSend implements Runnable{
	private DatagramSocket datagramSocket;
	private int localPort;
	private String toHostName;
	private int toPort;

	public TalkSend(int localPort, String toHostName, int toPort){
		this.localPort = localPort;
		this.toHostName = toHostName;
		this.toPort = toPort;
		try {
			datagramSocket = new DatagramSocket(localPort);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			// 2. 准备数据，一定要转换成**字节数组**
			BufferedInputStream bis = new BufferedInputStream(System.in);
			while (true){
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String data = reader.readLine();
				byte[] bytes = data.getBytes();

				// 3. 封装成 DatagramPacket 包裹，需要指定目的地（ip 和 port）
				InetSocketAddress destAdd = new InetSocketAddress(toHostName, toPort);
				DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length, destAdd);
				// 4. 发送包裹 send(DatagramPacket dp)
				datagramSocket.send(datagramPacket);
				if("bye".equals(data)){
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//  5. 释放资源
			datagramSocket.close();
		}
	}
}
