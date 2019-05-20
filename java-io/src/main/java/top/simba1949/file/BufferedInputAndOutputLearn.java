package top.simba1949.file;

import java.io.*;

/**
 * @author SIMBA1949
 * @date 2019/5/19 20:30
 */
public class BufferedInputAndOutputLearn {
	public static void main(String[] args) {
		readAndWriter();
	}

	public static void readAndWriter(){
		// 创建源
		String src = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/bufferedSrc.txt";
		File srcFile = new File(src);
		String dest = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/bufferedDest.txt";
		File destFile = new File(dest);

		// 选择流
		BufferedInputStream is = null;
		BufferedOutputStream os = null;

		try {
			is = new BufferedInputStream(new FileInputStream(srcFile));
			os = new BufferedOutputStream(new FileOutputStream(destFile));
			// 操作流
			byte[] flush = new byte[1024 * 8];
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
