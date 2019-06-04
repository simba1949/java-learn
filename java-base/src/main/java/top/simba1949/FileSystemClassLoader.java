package top.simba1949;

import java.io.*;

/**
 * 自定义文件系统类加载器
 * 1. 继承 ClassLoader
 * 2. 委托父类加载，父类加载不到自己加载
 *
 * @author SIMBA1949
 * @date 2019/6/4 21:54
 */
public class FileSystemClassLoader extends ClassLoader{

	private String rootDir;

	public FileSystemClassLoader(String rootDir) {
		this.rootDir = rootDir;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> loadedClass = findLoadedClass(name);
		// 判断是否加载
		if (loadedClass == null){
			ClassLoader parent = this.getParent();
			try {
				// 委托父类加载
				loadedClass = parent.loadClass(name);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			if (loadedClass == null){
				// 父类加载不到，自己加载
				byte[] classData = getClassData(name);
				if (classData == null){
					throw new ClassNotFoundException();
				}else {
					// 将类的字节数组，定义一个类
					loadedClass = defineClass(name, classData, 0, classData.length);
				}
			}
		}
		return loadedClass;
	}

	private byte[] getClassData(String classname) {
		String path = rootDir + "/" + classname.replace(".", "/") + ".class";
		FileInputStream fis = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			fis = new FileInputStream(new File(path));

			byte[] flush = new byte[1024 * 8];
			int len;
			while ((len = fis.read(flush)) != -1){
				baos.write(flush, 0, len);
			}
			return baos.toByteArray();
		} catch (IOException e){
			return null;
		} finally {
			if (null != baos){
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != fis){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
