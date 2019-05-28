package top.simba1949.socket.udp.interactive;

/**
 * @author SIMBA1949
 * @date 2019/5/26 13:27
 */
public class Student {
	public static void main(String[] args) {
		// 接收
		new Thread(new TalkReceive(8888,"老师")).start();

		// 发送
		new Thread(new TalkSend(8887, "localhost", 9998)).start();
	}
}
