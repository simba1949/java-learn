package top.simba1949.file;

import java.io.*;

import static java.lang.System.*;

/**
 * @author SIMBA1949
 * @date 2019/5/21 8:05
 */
public class PrintStreamLearn {
	public static void main(String[] args) throws FileNotFoundException {
//		printStream();
		printWriter();
	}
	
	public static void printStream() throws FileNotFoundException {
		PrintStream ps = out;
		ps.println("打印流");
		ps.close();

		String str = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/printStream.txt";
		PrintStream printStream = new PrintStream(new BufferedOutputStream(new FileOutputStream(str)), true);
		// 打印流重定向到文件中
		System.setOut(printStream);
		printStream.println("重定向到文件中");
		printStream.close();

		// 在重定向到控制台
		System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)), true));
		System.out.println("重定向到控制台");

	}
	
	public static void printWriter() throws FileNotFoundException {
		String src = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/printWriter.txt";
		PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(new File(src))));
		printWriter.println("printWriter 流笔记");
		printWriter.flush();
		printWriter.close();
	}

	
}
