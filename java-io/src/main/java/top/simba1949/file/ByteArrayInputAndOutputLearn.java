package top.simba1949.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author SIMBA1949
 * @date 2019/5/20 7:31
 */
public class ByteArrayInputAndOutputLearn {
	public static void main(String[] args) {
		readAndWrite();
	}

	public static void readAndWrite(){
		// 创建源
		String srcStr = "God will you let her know that I love her so";
		byte[] src = srcStr.getBytes();

		// 选择流
		ByteArrayInputStream is = new ByteArrayInputStream(src);
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try{
			// 操作流
			byte[] flush = new byte[10];
			int len = -1;
			while ((len = is.read(flush)) != -1){
				// 每次读取 flush.length 字节数，但最终 os 拥有全部数据， os.toByteArray() 指的是 n 次读取数据的累加
				os.write(flush, 0, len);
				os.flush();
				byte[] bytes = os.toByteArray();
				System.out.println(new String(bytes, 0, bytes.length));
			}
		}catch (IOException e){
			e.printStackTrace();
		} finally {
			// 关闭流
			if (null != os){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (null != is){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
