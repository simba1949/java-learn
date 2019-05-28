package top.simba1949.locks;

/**
 * @author SIMBA1949
 * @date 2019/5/25 9:46
 */
public class DeadLock {
	public static void main(String[] args) {
		new MarkUp(1, "貂蝉").start();
		new MarkUp(0, "王昭君").start();
	}
}

class Lipstick{

}

class Mirro{

}

class MarkUp extends Thread{
	static Lipstick lipstick = new Lipstick();
	static Mirro mirro = new Mirro();
	int choice;
	String girl;

	public MarkUp(int choice, String girl){
		this.choice = choice;
		this.girl = girl;
	}

	@Override
	public void run() {
		markUp();
	}

	private void markUp() {
		if(choice == 0){
			synchronized (lipstick){
				System.out.println(this.girl + "获得口红");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (mirro){
					System.out.println(this.girl + "照镜子");
				}
			}
		}else{
			synchronized (mirro){
				System.out.println(this.girl + "照镜子");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (lipstick){
					System.out.println(this.girl + "获得口红");
				}
			}
		}
	}
}
