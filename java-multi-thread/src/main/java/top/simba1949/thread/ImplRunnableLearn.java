package top.simba1949.thread;

/**
 * @author SIMBA1949
 * @date 2019/5/23 8:21
 */
public class ImplRunnableLearn implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "多线程实现方式二，实现 Runnable 接口，重写run方法");
	}
}

class ImplRunnableLearnTest {
	public static void main(String[] args) {
		Runnable runnable = new ImplRunnableLearn();
		new Thread(runnable).start();
	}
}
