package server01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author SIMBA1949
 * @date 2019/5/30 8:19
 */
public class Request {

	private Socket client;
	private InputStream is;

	public Request(Socket client) {
		this.client = client;
		try {
			this.is = client.getInputStream();
			// for request by client socket
			forRequest();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void forRequest() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		// 请求行
		String temp;
		while ((temp = br.readLine()) != null){

		}
	}

	public String getRequestParams() {
		return null;
	}
}
