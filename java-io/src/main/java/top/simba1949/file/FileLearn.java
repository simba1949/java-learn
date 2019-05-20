package top.simba1949.file;

import java.io.*;

/**
 * @author SIMBA1949
 * @date 2019/5/19 13:28
 */
public class FileLearn {
	public static void main(String[] args) {
//		fileConstruction();
//		createFile();
		getFileField();
	}

	public static void fileConstruction(){
		// 通过全路径创建 File 对象
		File fileOne = new File("T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/file.txt");
		System.out.println("通过全路径创建 File 对象：" + fileOne.getName());

		// 通过父路径 + 子文件或者文件夹路径 创建 File 对象
		String parentStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file";
		File fileTwo = new File(parentStr, "fileLearn.txt");
		System.out.println("通过父路径 + 子文件或者文件夹路径 创建 File 对象：" + fileTwo.getName());

		// 通过 父File 对象 + 子文件或者文件夹路径 创建 File 对象
		File parentFile = new File("T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file");
		File fileThree = new File(parentFile, "fileLearn.txt");
		System.out.println("通过 父File 对象 + 子文件或者文件夹路径 创建 File 对象：" + fileThree.getName());
	}

	public static void createFile(){
		try {
			// 创建文件
			File file = new File("T:\\IDE\\IDEA\\Workspace\\java-learn\\java-io\\src\\main\\resources\\file\\createdFile.md");
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 创建一级文件夹
		File dir = new File("T:\\IDE\\IDEA\\Workspace\\java-learn\\java-io\\src\\main\\resources\\file\\newDir");
		dir.mkdir();

		// 创建多级文件夹
		File dirs = new File("T:\\IDE\\IDEA\\Workspace\\java-learn\\java-io\\src\\main\\resources\\file\\oneDirs\\twoDirs");
		dirs.mkdirs();
	}

	public static void getFileField(){
		File file = new File("T:\\IDE\\IDEA\\Workspace\\java-learn\\java-io\\src\\main\\resources\\file\\file2.md");
		// 获取文件/文件夹名称
		System.out.println("获取文件/文件夹名称：" + file.getName());
		// 获取文件/文件夹的绝对路径
		System.out.println("获取文件/文件夹的绝对路径：" + file.getAbsolutePath());
		// 获取文件/文件夹的路径，可能是绝对路径，也可能是相对路径
		System.out.println("获取文件/文件夹的路径，可能是绝对路径，也可能是相对路径：" + file.getPath());
		// 获取文件/文件夹字节数
		System.out.println("获取文件/文件夹字节数：" + file.length());
		// 判断该 File 对象是不是文件
		System.out.println("判断该 File 对象是不是文件：" + file.isFile());
		// 判断该 File 对象是不是文件夹
		System.out.println("判断该 File 对象是不是文件夹：" + file.isDirectory());
		// 判断文件或者文件夹是否存在
		System.out.println("判断文件或者文件夹是否存在：" + file.exists());
		// 删除文件/文件夹
//		System.out.println("删除文件/文件夹是否成功" + file.delete());
		// 重命名，如果路径相同只做改名，如果路径不同剪切并改名
//		System.out.println("重命名" + file.renameTo(new File("T:\\IDE\\IDEA\\Workspace\\java-learn\\java-io\\src\\main\\resources\\file\\file.txt")));
		// 获取 File 对象下所有 File 对象，文件夹会列出来
		File[] files = file.listFiles();
		if (null != files){
			for (File item : files) {
				System.out.println("item.getName() = " + item.getName());
			}
		}

	}
}
