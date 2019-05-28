package top.simba1949.socket.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 创建一个客户端
 * 1.使用 Socket 创建客户端，需要指定 服务地址 + 端口，建立连接
 * 2.操作：输入输出流操作
 * 3.释放资源
 *
 * @author SIMBA1949
 * @date 2019/5/26 15:21
 */
public class TCPClient {
	public static void main(String[] args) throws IOException {
		// 1.使用 Socket 创建客户端，需要指定 服务地址 + 端口，建立连接
		Socket client = new Socket("localhost", 8888);
		// 2.操作：输入输出流操作
		OutputStream os = client.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		dos.writeInt(9);
		dos.writeUTF("hello");
		dos.flush();
		// 3.释放资源
		os.close();
		dos.close();
		client.close();
	}
}
