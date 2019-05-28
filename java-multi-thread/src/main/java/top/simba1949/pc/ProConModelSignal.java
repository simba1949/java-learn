package top.simba1949.pc;

/**
 * 生产者-消费者模型——信号灯法
 *
 * @author SIMBA1949
 * @date 2019/5/25 10:59
 */
public class ProConModelSignal {
	public static void main(String[] args) {
		Single single = new Single();
		new Player(single).start();
		new Watcher(single).start();
	}
}

/**
 * 生产者
 */
class Player extends Thread{
	private static final int MAX = 20;
	Single single;

	public Player(Single single) {
		this.single = single;
	}

	@Override
	public void run() {
		for (int i = 0; i < MAX; i++){
			if (i%2 == 0){
				this.single.play("奇葩说");
			}else {
				this.single.play("广告时间");
			}
		}
	}
}

/**
 * 消费者
 */
class Watcher extends  Thread{
	private static final int MAX = 20;
	Single single;

	public Watcher(Single single) {
		this.single = single;
	}

	@Override
	public void run() {
		for (int i = 0; i < MAX; i++){
			single.watch();
		}
	}
}

/**
 * 同一资源
 */
class Single{
	String voice;
	/**
	 * 信号灯
	 * true 表示演员表演，观众等待
	 * false 表示观众观看，演员等待
	 */
	boolean flag = true;

	/**
	 * 表演
	 */
	public synchronized void play(String voice){
		// 演员等待
		if (!flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 表演
		System.out.println("表演了" + voice);
		this.voice = voice;
		// 唤醒
		this.notifyAll();
		// 切换标志
		this.flag = !this.flag;
	}

	/**
	 * 观看
	 */
	public synchronized void watch(){
		// 观众等待
		if (flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("观看到啦" + voice);
		// 唤醒
		this.notifyAll();
		// 切换标志
		this.flag = !this.flag;
	}
}


