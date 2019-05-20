package top.simba1949.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author SIMBA1949
 * @date 2019/5/19 17:28
 */
public class FileOutputStreamLearn {
	public static void main(String[] args) {
		writer();
	}

	public static void writer(){
		// 创建源
		String fileStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/osWriter.txt";
		File file = new File(fileStr);

		String msg = "I know that she's out there...the one I'm suppose to share my whole life with.\n";
		byte[] src = msg.getBytes();

		FileOutputStream os = null;
		try {
			// 选择流，写入是否追加，默认 false 表示重写， true 表示在文件后面追加
			os = new FileOutputStream(file, true);
			os.write(src, 0, src.length);
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
