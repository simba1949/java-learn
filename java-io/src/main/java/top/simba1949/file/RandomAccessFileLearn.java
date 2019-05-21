package top.simba1949.file;

import java.io.*;

/**
 * @author SIMBA1949
 * @date 2019/5/21 22:15
 */
public class RandomAccessFileLearn {
	public static void main(String[] args) throws IOException {
//		randomAccessFile();
		splitFileApp();
	}

	/**
	 * 体验 RandomAccessFile.seek(偏移量) 读取数据
	 * @throws IOException
	 */
	public static void randomAccessFile() throws IOException {
		String src = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/randomAccessFileSrc.txt";
		RandomAccessFile rw = new RandomAccessFile(new File(src), "rw");
		rw.seek(2);
		byte[] flush = new byte[1024];
		int len = -1;
		while ((len = rw.read(flush)) != -1){
			System.out.println(new String(flush, 0, len));
			rw.write(flush, 0, len);
		}
		rw.close();
	}

	/**
	 * 拆分文件
	 * @throws IOException
	 */
	public static void splitFileApp() throws IOException {
		String src = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/random/src/randomAccessFileSrc.txt";
		File file = new File(src);
		// 文件总字节数
		long length = file.length();
		// 每块区域的大小
		int blockSize = 200;
		// 多少块
		int totalNum = (int) (length % blockSize == 0 ? length/blockSize : length/blockSize + 1);

		for (int i = 0; i < totalNum; i++){
			if (i == (totalNum - 1)){
				// 说明是最后一块
				// 考虑最后一块区域
				int lastBlockSize = (int) (length % blockSize == 0 ? blockSize : (length - (length/blockSize) * blockSize));
				read(i, blockSize, lastBlockSize, file);
			}else {
				// 中间
				read(i, blockSize, blockSize, file);
			}
		}
	}

	public static void read(int blockNum, int blockSize, int readLength, File file) throws IOException {
		String dest = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/random/split/" + blockNum + ".tmp";
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(dest)));
		RandomAccessFile ra = new RandomAccessFile(file, "rw");
		ra.seek(blockNum * blockSize);
		byte[] flush = new byte[readLength];
		ra.read(flush);
		bos.write(flush, 0, readLength);
		bos.close();
		ra.close();
	}
}
