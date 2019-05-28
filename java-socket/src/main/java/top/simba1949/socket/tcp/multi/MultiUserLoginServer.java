package top.simba1949.socket.tcp.multi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author SIMBA1949
 * @date 2019/5/26 16:34
 */
public class MultiUserLoginServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8888);
		new Thread(new Channel(serverSocket)).start();
	}

	static class Channel implements Runnable{

		private InputStream is;
		private OutputStream os;
		private ServerSocket serverSocket;
		private Socket accept;

		public Channel(ServerSocket serverSocket) {
			this.serverSocket = serverSocket;
		}

		@Override
		public void run() {
			while (true){
				try {
					accept = serverSocket.accept();
					is = accept.getInputStream();
					String str = Msg.getMsg(is);
					String[] split = str.split("&");

					String msg = "";
					// 逻辑判断
					if("user".equals(split[0].split("=")[1]) && "pwd".equals(split[1].split("=")[1])){
						msg = "登录成功，欢迎回家";
					}else {
						msg = "密码或者账号错误";
					}

					os = accept.getOutputStream();
					Msg.outMsg(os, msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

