package top.simba1949.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author SIMBA1949
 * @date 2019/5/20 12:50
 */
public class FileWriterLearn {
	public static void main(String[] args) {
		write();
	}
	
	public static void write(){
		// 创建输出源
		String dest = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/fileWriter.txt";
		File destFile = new File(dest);
		// 字符
		String src = "天道酬勤，the time of show me code";
		
		// 选择流
		FileWriter writer = null;
		try {
			writer = new FileWriter(destFile);
			// 操作流
			writer.write(src, 0, src.length());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
