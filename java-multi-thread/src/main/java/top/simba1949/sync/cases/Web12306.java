package top.simba1949.sync.cases;

/**
 * @author SIMBA1949
 * @date 2019/5/25 8:50
 */
public class Web12306 implements Runnable{

	/**
	 * 设置总票数
	 */
	private int ticketNums = 99;

	@Override
	public void run() {
		while (true){
			if (ticketNums < 0){
				break;
			}
			try {
				// 模拟网络延迟
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "---->" + ticketNums--);
		}
	}
}
class Web12306Test {
	public static void main(String[] args) {
		// 创建一份资源
		Web12306 web12306 = new Web12306();
		// 多线程
		new Thread(web12306).start();
		new Thread(web12306).start();
		new Thread(web12306).start();
		new Thread(web12306).start();
	}
}
