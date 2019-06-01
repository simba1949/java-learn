package server01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author SIMBA1949
 * @date 2019/5/30 8:16
 */
public class Channel implements Runnable {

	private ServerSocket server;
	private Socket client;

	public Channel(ServerSocket server) {
		this.server = server;
		try {
			this.client = server.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// 获取请求参数，key=value&形式传输
		Request request = new Request(client);
		String requestParams = request.getRequestParams();
	}
}
