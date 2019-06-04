package top.simba1949;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.Set;

/**
 * @author SIMBA1949
 * @date 2019/5/27 16:39
 */
public class App {
	public static void main(String[] args) throws IOException {
		javaCompiler("T:/IDE/IDEA/Workspace/java-learn/java-reflect/src/main/java/top/simba1949/HelloWorld.java");

		runtime();
	}

	public static void runtime(){
		Runtime runtime = Runtime.getRuntime();
		Process process = null;
		try {
			process = runtime.exec("java  -cp  T:/IDE/IDEA/Workspace/java-learn/java-reflect/src/main/java/top/simba1949/    HelloWorld");
		} catch (IOException e) {
			e.printStackTrace();
		}

		InputStream inputStream = process.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String temp;
		try {
			while ((temp = reader.readLine()) != null){
				System.out.println(temp);
			}
		} catch (IOException e){
			System.out.println(e.getMessage());
		}



	}

	public static void javaCompiler(String sourceFile){
		JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
		// 第一个参数：为 Java 编译器提供参数
		// 第二个参数：得到 Java 编译器的输出信息
		// 第三个参数：接收编译器的错误信息
		// 第四个参数：可变参数（字符串数组），能传一个或者多个 Java 源文件
		// result 为 0 表示编译成功，非 0 表示编译失败
		int result = javaCompiler.run(null, null, null, sourceFile);
		System.out.println(result == 0 ? "编译成功" : "编译失败");
	}
}
