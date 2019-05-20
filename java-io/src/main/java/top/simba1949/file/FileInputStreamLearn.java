package top.simba1949.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author SIMBA1949
 * @date 2019/5/19 16:55
 */
public class FileInputStreamLearn {
	public static void main(String[] args) {
//		singleByteRead();
		bufferedRead();
	}

	/**
	 * 单字节读取数据
	 */
	public static void singleByteRead(){
		// 创建源
		String fileStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/file.txt";
		File file = new File(fileStr);
		// 选择流
		FileInputStream is = null;
		try {
			is = new FileInputStream(file);
			// 对流进行操作，每读取一个字节，将字节值赋值给 temp，读到没有值返回 -1
			int temp = -1;
			while ((temp = is.read()) != -1){
				System.out.print((char)temp);
			}
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			if (null != is){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 使用自定义缓存区读取数据
	 */
	public static void bufferedRead(){
		// 创建源
		String fileStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/file.txt";
		File file = new File(fileStr);

		// 选择流
		FileInputStream is = null;
		try {
			is = new FileInputStream(file);
			byte[] flush = new byte[8];
			int len = -1;
			// 每次读取 flush.length 字节数，并存储在 flush 中
			while ((len = is.read(flush)) != -1){
				for (byte ele : flush) {
					System.out.print((char)ele);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
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
