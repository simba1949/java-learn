package top.simba1949.file;

import java.io.*;

/**
 * @author SIMBA1949
 * @date 2019/5/20 21:27
 */
public class InputStreamReaderLearn {
	public static void main(String[] args) {
		singleRead();
//		flushRead();
	}

	/**
	 * 单字符读取
	 */
	public static void singleRead(){
		// 1.创建源
		String srcStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/inputStreamReader.txt";
		File srcFile = new File(srcStr);
		// 2. 选择流
		InputStreamReader is = null;
		try {
			is = new InputStreamReader(new FileInputStream(srcFile));
			// 3.操作流
			int temp = -1;
			while ((temp = is.read()) != -1){
				System.out.print((char) temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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
	 * 自定义缓存读取
	 */
	public static void flushRead(){
		// 1.创建源
		String srcStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/inputStreamReader.txt";
		File srcFiel = new File(srcStr);
		// 2.选择流
		InputStreamReader is = null;
		try {
			is = new InputStreamReader(new FileInputStream(srcFiel));
			// 3.操作流
			char[] flush = new char[10];
			int len = -1;
			while ((len = is.read(flush)) != -1){
				System.out.print(new String(flush, 0, len));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			// 4.关闭流
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
