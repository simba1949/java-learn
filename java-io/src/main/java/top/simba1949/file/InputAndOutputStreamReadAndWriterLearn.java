package top.simba1949.file;

import java.io.*;

/**
 * @author SIMBA1949
 * @date 2019/5/20 21:59
 */
public class InputAndOutputStreamReadAndWriterLearn {
	public static void main(String[] args) {
		readAndWrite();
	}
	
	public static void readAndWrite(){
		// 1.创建源
		String srcPath = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/InputAndOutputStreamReadAndWriterSrc.txt";
		File srcFile = new File(srcPath);
		String destPath = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/InputAndOutputStreamReadAndWriterDest.txt";
		File destFile = new File(destPath);
		// 2.选择流
		InputStreamReader is = null;
		OutputStreamWriter os = null;

		try {
			is = new InputStreamReader(new FileInputStream(srcFile));
			os = new OutputStreamWriter(new FileOutputStream(destFile));
			// 3.操作流
			char[] flush = new char[10];
			int len = -1;
			while ((len = is.read(flush)) != -1){
				os.write(flush, 0, len);
			}
			os.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			// 4.关闭流
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
