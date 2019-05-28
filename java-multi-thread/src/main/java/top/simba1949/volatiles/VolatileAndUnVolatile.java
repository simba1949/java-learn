package top.simba1949.volatiles;

/**
 * volatile 只保证数据的同步，可以避免指令重排
 *
 * @author SIMBA1949
 * @date 2019/5/25 12:13
 */
public class VolatileAndUnVolatile {
	public static void main(String[] args) {

	}
}

class UnVolatile{
	private static int NUM = 0;
	public static void main(String[] args) throws InterruptedException {
		new Thread(()->{
			while (NUM == 0){

			}
		}).start();

		Thread.sleep(1000);
		NUM = 1;
	}
}

class MyVolatile{
	private volatile static int NUM = 0;
	public static void main(String[] args) throws InterruptedException {
		new Thread(()->{
			while (NUM == 0){

			}
		}).start();

		Thread.sleep(1000);
		NUM = 1;
	}
}
