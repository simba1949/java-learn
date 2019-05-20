package top.simba1949.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author SIMBA1949
 * @date 2019/5/20 13:14
 */
public class BufferedWriterLearn {
	public static void main(String[] args) {
		writer();
	}
	
	public static void writer(){
		// 1.创建源
		String dest = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/bufferedWriter.txt";
		File destFile = new File(dest);
		// 2.选择流
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(destFile));
			String src = "dear god, 哦，我亲爱的上帝";
			// 3.操作流
			writer.write(src);
			writer.flush();
		} catch (IOException e) {
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
		}
	}
}
