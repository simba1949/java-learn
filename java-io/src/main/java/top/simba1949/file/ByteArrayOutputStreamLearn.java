package top.simba1949.file;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author SIMBA1949
 * @date 2019/5/19 21:05
 */
public class ByteArrayOutputStreamLearn {
	public static void main(String[] args) {
		writer();
	}

	public static void writer(){
		String msg = "When the darkness falls will you please shine her the way (shine he the way)\n";
		// 创建源
		byte[] bytes = null;

		// 选择流
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			os.write(msg.getBytes());
			byte[] destSrc = os.toByteArray();
			String s = new String(destSrc, 0, destSrc.length);
			System.out.println(s);os.flush();
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (null != os){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
