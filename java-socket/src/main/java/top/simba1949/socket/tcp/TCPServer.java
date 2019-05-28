package top.simba1949.socket.tcp;

import javax.sound.midi.Soundbank;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建服务器
 * 1. 指定端口，使用 ServerSocket 创建服务器
 * 2. 阻塞式等待连接 accept
 * 3. 操作：输入输出流操作
 * 4. 释放资源
 *
 * @author SIMBA1949
 * @date 2019/5/26 15:17
 */
public class TCPServer {
	public static void main(String[] args) throws IOException {
		// 1. 指定端口，使用 ServerSocket 创建服务器
		ServerSocket serverSocket = new ServerSocket(8888);
		// 2. 阻塞式等待连接 accept
		Socket accept = serverSocket.accept();
		System.out.println("一个客户端建立连接");
		// 3. 操作：输入输出流操作
		InputStream is = accept.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		System.out.println(dis.readInt());
		System.out.println(dis.readUTF());
		// 4. 释放资源
		dis.close();
		serverSocket.close();
	}
}
