package top.simba1949.socket.tcp;

import java.io.*;
import java.net.Socket;

/**
 * @author SIMBA1949
 * @date 2019/5/26 15:53
 */
public class RequestAndResponseClient {
	public static void main(String[] args) throws IOException {
		// 1、指定地址和端口，创建 Socket 对象，此时已经建立连接
		Socket socket = new Socket("localhost", 8888);
		// 操作流：操作输入输出流
		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("请求输入用户名： ");
		String userName = reader.readLine();
		System.out.print("请求输入密码： ");
		String password = reader.readLine();
		dos.writeUTF("user=" + userName + "&pwd=" + password);


		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		System.out.println("获取服务器响应结果为： " + dis.readUTF());
		dos.close();
		os.close();
		dis.close();
		socket.close();
	}
}
