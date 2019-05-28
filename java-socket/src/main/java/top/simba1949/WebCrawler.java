package top.simba1949;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author SIMBA1949
 * @date 2019/5/26 11:19
 */
public class WebCrawler {
	public static void main(String[] args) throws IOException {
		URL url = new URL("https://blog.csdn.net/simba1949");
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		// 设置请求方式
		urlConnection.setRequestMethod("GET");
		// 模拟浏览器
		urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
		InputStream inputStream = url.openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
		int temp;
		while ((temp = reader.read()) != -1){
			System.out.print((char)temp);
		}
		reader.close();
	}
}
