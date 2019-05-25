package top.simba1949.thread;

import java.util.concurrent.*;

/**
 * @author SIMBA1949
 * @date 2019/5/23 8:21
 */
public class ImplCallable implements Callable<Object> {
	/**
	 * 实现 Callable 接口，重写 call 方法
	 * @return
	 * @throws Exception
	 */
	@Override
	public Object call() throws Exception {
		System.out.println(Thread.currentThread().getName() + "多线程实现方式三：实现 Callable 接口");
		return "SUCCESS";
	}
}

class ImplCallableTest{
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// 创建实现 Callable 接口的对象
		ImplCallable implCallable = new ImplCallable();
		// 创建一个线程数为 3 的线程池
		ExecutorService pool = Executors.newFixedThreadPool(3);
		// 提交执行
		Future<Object> result = pool.submit(implCallable);
		// 获取执行结果
		Object o = result.get();
		System.out.println(o.toString());
		// 关闭服务
		pool.shutdown();
	}
}
