package top.simba1949.file;

import java.io.*;

/**
 * @author SIMBA1949
 * @date 2019/5/19 17:54
 */
public class BufferedInputStreamLearn {
	public static void main(String[] args) {
//		singleBufferedRead();
		flushBufferedRead();
	}

	/**
	 * 单字节读取
	 */
	public static void singleBufferedRead(){
		// 创建源
		String src = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/bufferedInput.txt";
		File file = new File(src);

		// 选择流
		BufferedInputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream(file));
			// 操作流
			int temp = -1;
			while((temp = is.read()) != -1){
				System.out.print((char) temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			// 关闭流
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
	public static void flushBufferedRead(){
		// 创建源
		String srcStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/bufferedInput.txt";
		File file = new File(srcStr);

		BufferedInputStream is = null;
		try {
			// 选择流
			is = new BufferedInputStream(new FileInputStream(file));
			// 操作流
			byte[] flush = new byte[1024];
			int len = -1;
			while ((len = is.read(flush)) != -1){
				for (byte ele : flush) {
					System.out.print((char)ele);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			// 关闭流
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
