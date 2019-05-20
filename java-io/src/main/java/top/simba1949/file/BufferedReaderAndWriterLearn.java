package top.simba1949.file;

import java.io.*;

/**
 * @author SIMBA1949
 * @date 2019/5/20 13:19
 */
public class BufferedReaderAndWriterLearn {
	public static void main(String[] args) {
		readAndWrite();
	}
	
	public static void readAndWrite(){
		// 1.创建源
		String srcStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/bufferedReadAndWriterSrc.txt";
		File srcFile = new File(srcStr);
		String destSrc = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/bufferedReadAndWriterDest.txt";
		File destFile = new File(destSrc);
		// 2.选择流
		BufferedReader reader = null;
		BufferedWriter writer = null;

		try {
			reader = new BufferedReader(new FileReader(srcFile));
			writer = new BufferedWriter(new FileWriter(destFile));
			// 3.操作流
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
			// 4.关闭流
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
