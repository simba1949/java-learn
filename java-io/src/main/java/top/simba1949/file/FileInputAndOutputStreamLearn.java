package top.simba1949.file;

import java.io.*;

/**
 * @author SIMBA1949
 * @date 2019/5/19 17:45
 */
public class FileInputAndOutputStreamLearn {
	public static void main(String[] args) {
		readAndWiter();
	}

	public static void readAndWiter(){
		// 创建读取的源，和写入的源
		String srcStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/input.txt";
		File srcFile = new File(srcStr);
		String destStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/output.txt";
		File destFile = new File(destStr);

		// 选择流
		FileInputStream is = null;
		FileOutputStream os = null;

		try {
			is = new FileInputStream(srcFile);
			os = new FileOutputStream(destFile);

			byte[] flush = new byte[1024 * 8];
			int len = -1;
			while ((len = is.read(flush)) != -1){
				os.write(flush, 0 ,len);
			}
			os.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			// 关闭流
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
