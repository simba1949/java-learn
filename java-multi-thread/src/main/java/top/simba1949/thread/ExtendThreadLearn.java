package top.simba1949.thread;

/**
 * @author SIMBA1949
 * @date 2019/5/23 8:21
 */
public class ExtendThreadLearn extends Thread {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "多线程实现方式一，继承 Thread， 重写run方法");
	}
}

class ExtendThreadLearnTest {
	public static void main(String[] args) {
		ExtendThreadLearn td = new ExtendThreadLearn();
		td.start();
	}
}