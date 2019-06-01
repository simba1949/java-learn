package server01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author SIMBA1949
 * @date 2019/5/29 10:16
 */
public class Server {
	private ServerSocket serverSocket;
	private int port;

	public Server(int port){
		this.port = port;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ServerSocket start(int port){
		Server server = new Server(port);
		return server.getServerSocket();
	}
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = Server.start(8888);


		Socket client = serverSocket.accept();
		System.out.println("一个客户端建立了连接");
		InputStream is = client.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		char[] flush = new char[1024 * 8];
		int len;
		while ((len = br.read(flush)) != -1){
			System.out.println(new String(flush, 0, len));
		}

	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}
}
