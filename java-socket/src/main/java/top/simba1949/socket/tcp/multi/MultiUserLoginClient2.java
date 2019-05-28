package top.simba1949.socket.tcp.multi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author SIMBA1949
 * @date 2019/5/26 16:34
 */
public class MultiUserLoginClient2 {
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("192.168.232.128", 8888);
		OutputStream os = socket.getOutputStream();
		String username = "user2";
		String password = "pwd2";
		String msg = "user=" + username + "&pwd=" + password;
		Msg.outMsg(os, msg);
		InputStream is = socket.getInputStream();
		Msg.getMsg(is);

		is.close();
		os.close();
		socket.close();
	}
}
