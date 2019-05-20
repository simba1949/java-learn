package top.simba1949.file;

import java.io.*;

/**
 * @author SIMBA1949
 * @date 2019/5/19 20:13
 */
public class BufferedOutputStreamLearn {
	public static void main(String[] args) {
		writer();
	}

	public static void writer(){
		// 创建输出源
		String srcStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/bufferedOutput.txt";
		File file = new File(srcStr);
		// 创建输入源
		String msg = "When the darkness falls will you please shine her the way (shine he the way)";

		BufferedOutputStream os = null;
		try {
			// 选择流
			os = new BufferedOutputStream(new FileOutputStream(file));
			// 操作流
			os.write(msg.getBytes());
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
		}
	}
}
