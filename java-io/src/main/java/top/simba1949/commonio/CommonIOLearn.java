package top.simba1949.commonio;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author SIMBA1949
 * @date 2019/5/21 23:44
 */
public class CommonIOLearn {
	public static void main(String[] args) {
		// 统计字节数
		long fileLen = FileUtils.sizeOf(new File(""));
		// 获取指定目录所有文件
		Collection<File> listFiles = FileUtils.listFiles(
				// 指定路径
				new File(""),
				// 文件过滤
				FileFilterUtils.and(new SuffixFileFilter("java")),
				// 文件夹过滤
				DirectoryFileFilter.INSTANCE);

		try {
			// 指定字符集读取文件
			String fileString = FileUtils.readFileToString(new File(""), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			// 读取每行数据
			List<String> strings = FileUtils.readLines(new File(""), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			// 将文件读取为字节数组
			byte[] bytes = FileUtils.readFileToByteArray(new File(""));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			//
			LineIterator lineIterator = FileUtils.lineIterator(new File(""));
			while (lineIterator.hasNext()){
				String next = lineIterator.next();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			// 将字符串写到文件中
			String data = "";
			FileUtils.write(new File(""), data,"utf-8", true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			// 将字节数组写到文件中
			byte[] data = new byte[10];
			FileUtils.writeByteArrayToFile(new File(""), data);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			// 写列表
			List<String> list = new ArrayList<String>();
			FileUtils.writeLines(new File(""), list, true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			// 拷贝文件
			FileUtils.copyFile(new File("srcFile"), new File("destFile"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			// 拷贝文件夹
			FileUtils.copyDirectory(new File("srcDir"), new File("destDir"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			// 拷贝文件到文件夹中
			FileUtils.copyFileToDirectory(new File("srcFile"), new File("destDir"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			// 拷贝文件夹到文件夹下
			FileUtils.copyDirectoryToDirectory(new File("srcDir"), new File("destDir"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			String datas = IOUtils.toString(new URL("https://www.baidu.com"), "UTF-8");
			System.out.println(datas);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
