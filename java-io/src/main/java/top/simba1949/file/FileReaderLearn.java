package top.simba1949.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author SIMBA1949R
 * @date 2019/5/20 10:01
 */
public class FileReaderLearn {
	public static void main(String[] args) {
//		singleCharReader();
		flushRead();
	}

	/**
	 * 单字符读取
	 */
	public static void singleCharReader(){
		// 创建源
		String srcStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/fileReader.txt";
		File srcFile = new File(srcStr);
		
		// 选择流
		FileReader reader = null;
		try {
			reader = new FileReader(srcFile);
			int temp;
			while ((temp = reader.read()) != -1){
				System.out.println("temp:" + temp + (char) temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
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
		String srcStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/fileReader.txt";
		File file = new File(srcStr);
		
		// 选择流
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			// 操作流
			char[] flush = new char[10];
			int len = -1;
			while ((len = reader.read(flush)) != -1){
				System.out.print(new String(flush,0, len));
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
