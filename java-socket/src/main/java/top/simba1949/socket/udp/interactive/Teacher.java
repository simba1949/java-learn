package top.simba1949.socket.udp.interactive;

/**
 * @author SIMBA1949
 * @date 2019/5/26 13:27
 */
public class Teacher {
	public static void main(String[] args) {
		// 发送
		new Thread(new TalkSend(9999, "localhost", 8888)).start();

		// 接收
		new Thread(new TalkReceive(9998,"学生")).start();
	}
}
