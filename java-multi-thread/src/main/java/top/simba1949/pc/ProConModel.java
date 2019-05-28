package top.simba1949.pc;

/**
 * 生产者-消费者模型——管程法
 *
 * @author SIMBA1949
 * @date 2019/5/25 10:22
 */
public class ProConModel {
	public static void main(String[] args) {
		SyncContainer syncContainer = new SyncContainer();
		new Producer(syncContainer).start();
		new Consumer(syncContainer).start();
	}
}

/**
 * 生产者
 */
class Producer extends Thread{
	private static final int MAX_COUNT = 1000;
	SyncContainer syncContainer;

	public Producer(SyncContainer syncContainer){
		this.syncContainer = syncContainer;
	}

	@Override
	public void run() {
		for (int i = 0; i < MAX_COUNT; i++){
			syncContainer.push(new SteamedBead(i));
			System.out.println("生产者——>生产编号为< " + i + " >的馒头");
		}
	}
}

/**
 * 消费者
 */
class Consumer extends Thread{
	private static final int MAX_COUNT = 1000;
	SyncContainer syncContainer;

	public Consumer(SyncContainer syncContainer){
		this.syncContainer = syncContainer;
	}

	@Override
	public void run() {
		for (int i = 0; i < MAX_COUNT; i++){
			System.out.println("消费者——>消费编号为< " + i + " >的馒头");
			syncContainer.pop();
		}
	}
}

/**
 * 缓存区
 */
class SyncContainer{
	private static final int MAX_COUNT = 10;
	private static final int MIN_COUNT = 0;
	/**
	 * 	存储数据的容器
	 */
	SteamedBead[] steamedBeads = new SteamedBead[MAX_COUNT];
	/**
	 * 计数器
	 */
	int count = 0;

	public SyncContainer() {
	}

	public SyncContainer(SteamedBead[] steamedBeads) {
		this.steamedBeads = steamedBeads;
	}

	/**
	 * 生产
	 * @param steamedBead
	 */
	public synchronized void push(SteamedBead steamedBead){
		// 如果容器中没有可用空间，就不能进行生产
		if (count == MAX_COUNT){
			try {
				// 线程阻塞
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		steamedBeads[count] = steamedBead;
		count++;
		// 通知消费者可以消费
		this.notifyAll();
	}

	/**
	 * 消费
	 */
	public synchronized void pop(){
		// 如果容器中的馒头数目为零时，消费者就不能消费
		if (count == MIN_COUNT){
			try {
				// 线程阻塞
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count--;
		// 通知生产者可以生产
		this.notifyAll();
	}
}

/**
 * 馒头
 */
class SteamedBead{
	int id;

	public SteamedBead(int id) {
		this.id = id;
	}
}