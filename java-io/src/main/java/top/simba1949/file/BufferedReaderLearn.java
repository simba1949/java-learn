package top.simba1949.file;

import java.io.*;

/**
 * @author SIMBA1949
 * @date 2019/5/20 13:04
 */
public class BufferedReaderLearn {
	public static void main(String[] args) {
//		singleCharRead();
		flushRead();
	}

	/**
	 * 单个字符读取
	 */
	public static void singleCharRead(){
		// 创建源
		String src  = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/bufferedReader.txt";
		File srcFile = new File(src);
		
		// 选择流
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(srcFile));
			// 操作流
			int temp = -1;
			while ((temp = reader.read()) != -1){
				System.out.print((char)temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			// 关闭流
			if (null != reader){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 自定义缓存读取
	 */
	public static void flushRead(){
		// 创建源
		String src = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/bufferedReader.txt";
		File srcFile = new File(src);
		
		// 选择流
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(srcFile));
			char[] flush = new char[10];
			int len = -1;
			while ((len = reader.read(flush)) != -1){
				System.out.print(new String(flush, 0, len));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			// 关闭流
			if (null != reader){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
