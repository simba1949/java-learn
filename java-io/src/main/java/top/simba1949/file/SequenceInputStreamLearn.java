package top.simba1949.file;

import java.io.*;
import java.util.Vector;

/**
 * @author SIMBA1949
 * @date 2019/5/21 23:28
 */
public class SequenceInputStreamLearn {
	public static void main(String[] args) throws IOException {
		write();
	}
	
	public static void write() throws IOException {
		String splitDriPath = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/random/split";
		File file = new File(splitDriPath);

		// 创建输出源
		String destPath = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/random/group/group.txt";
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destPath, true));

		Vector<InputStream> vector = new Vector<InputStream>();
		SequenceInputStream sis = null;
		for (int i = 0; i < file.listFiles().length; i++){
			vector.add(new BufferedInputStream(new FileInputStream(new File(splitDriPath + "/" + i + ".tmp"))));
		}
		sis = new SequenceInputStream(vector.elements());
		
		byte[] flush = new byte[1024];
		int len = -1;
		while ((len = sis.read(flush)) != -1){
			bos.write(flush, 0, len);
		}
		bos.flush();
		bos.close();
		sis.close();
	}
}
