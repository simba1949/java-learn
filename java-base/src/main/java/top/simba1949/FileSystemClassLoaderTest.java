package top.simba1949;

/**
 * @author SIMBA1949
 * @date 2019/6/4 22:18
 */
public class FileSystemClassLoaderTest {
	public static void main(String[] args) throws ClassNotFoundException {
		FileSystemClassLoader fscl = new FileSystemClassLoader("T:");
		Class<?> aClass = fscl.findClass("top.simba1949.HelloWorld");
	}
}
