package top.simba1949.socket.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author SIMBA1949
 * @date 2019/5/26 15:53
 */
public class RequestAndResponseServer {
	public static void main(String[] args) throws IOException {
		// 1. 指定端口创建 ServerSocket 对象
		ServerSocket serverSocket = new ServerSocket(8888);
		// 2.阻塞式接收数据
		Socket accept = serverSocket.accept();
		// 3.操作流：输入输出流的操作
		InputStream is = accept.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		String userInfo = dis.readUTF();
		System.out.println("获取请求信息为：" + userInfo);
		String[] split = userInfo.split("&");
		OutputStream os = accept.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		if ("user".equals(split[0].split("=")[1]) && "pwd".equals(split[1].split("=")[1])){
			// 登录成功
			dos.writeUTF("登录成功， 欢迎回家");
		}else {
			// 登录失败
			dos.writeUTF("用户名或者密码错误");
		}
		// 4.释放资源
		dos.close();
		os.close();
		serverSocket.close();
	}
}
