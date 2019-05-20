package top.simba1949.file;

import java.io.*;

/**
 * @author SIMBA1949
 * @date 2019/5/20 12:54
 */
public class FileReaderAndWriterLearn {
	public static void main(String[] args) {
		readerAndWriter();
	}
	
	public static void readerAndWriter(){
		// 创建输入源
		String srcStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/readerAndWriterSrc.txt";
		File srcFile = new File(srcStr);
		// 创建输出源
		String destStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/readerAndWriterDest.txt";
		File destFile = new File(destStr);
		
		// 选择流
		FileReader reader = null;
		FileWriter writer = null;

		try {
			reader = new FileReader(srcFile);
			writer = new FileWriter(destFile);
			
			// 操作流
			char[] flush = new char[10];
			int len = -1;
			while ((len = reader.read(flush)) != -1){
				writer.write(flush, 0, len);
			}
			writer.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			// 关闭流
			if (null != writer){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
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
