package top.simba1949.socket.tcp.multi;

import java.io.*;

/**
 * @author SIMBA1949
 * @date 2019/5/26 16:49
 */
public class Msg {


	public static String getMsg(InputStream is) throws IOException {
		DataInputStream dis = new DataInputStream(is);
		String str = dis.readUTF();
		System.out.println("获取客户端的信息为：" + str);
		return str;
	}

	public static void outMsg(OutputStream os, String msg) throws IOException {
		DataOutputStream dos = new DataOutputStream(os);
		dos.writeUTF(msg);
	}
}
