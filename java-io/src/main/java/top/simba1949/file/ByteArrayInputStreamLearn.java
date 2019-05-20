package top.simba1949.file;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author SIMBA1949
 * @date 2019/5/19 20:57
 */
public class ByteArrayInputStreamLearn {
	public static void main(String[] args) {
//		read();
		flushRead();
	}

	/**
	 * 单字节读取
	 */
	public static void read(){
		String src = "When the darkness falls will you please shine her the way (shine he the way)\n";
		// 创建源
		byte[] srcBytes = src.getBytes();

		// 选择流
		ByteArrayInputStream is = new ByteArrayInputStream(srcBytes);
		int temp = -1;
		while ((temp = is.read()) != -1){
			System.out.println((char)temp);
		}
		try {
			// 关闭流
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 自定义缓存读取数据
	 */
	public static void flushRead(){
		String src = "Whenthe";
		// 创建源
		byte[] srcBytes = src.getBytes();

		// 选择流
		ByteArrayInputStream is = new ByteArrayInputStream(srcBytes);
		try {
			// 每次读取多少字节数，len 表示每次读取字节数的实际大小，内容最后一次读取完成后，还有进行一次读取，但是读取内容为空，返回-1
			byte[] flush = new byte[2];
			int len = -1;
			while ((len = is.read(flush)) != -1){
				System.out.println(new String(flush, 0, len));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
