package top.simba1949.file;

import java.io.*;

/**
 * @author SIMBA1949
 * @date 2019/5/20 21:42
 */
public class OutputStreamWriterLearn {
	public static void main(String[] args) {
		write();
	}
	
	public static void write(){
		// 1.创建源
		String destStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/OnputStreamWriter.txt";
		File destFile = new File(destStr);
		
		// 2.选择流
		OutputStreamWriter os = null;
		try {
			os = new OutputStreamWriter(new FileOutputStream(destFile));
			String msg = "把乾坤留在我心中的那一刻，就注定我不会寂寞";
			// 3.操作流
			os.write(msg, 0, msg.length());
			os.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			
		} finally {
			// 4.关闭流
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
