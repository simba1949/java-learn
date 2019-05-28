package top.simba1949.sync.cases;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SIMBA1949
 * @date 2019/5/25 9:02
 */
public class HappyCinema {
	public static void main(String[] args) {
		List<Integer> available = new ArrayList<>();
		available.add(1);
		available.add(2);
		available.add(3);
		available.add(6);
		available.add(9);
		available.add(19);
		Cinema cinema = new Cinema(available, "happyCinema");

		List<Integer> seats1 = new ArrayList<>();
		seats1.add(1);
		seats1.add(2);

		List<Integer> seats2 = new ArrayList<>();
		seats2.add(4);
		seats2.add(5);
		seats2.add(6);

		new Thread(new HappyCustomer(cinema, seats1), "张三").start();
		new Thread(new HappyCustomer(cinema, seats2), "李四").start();
		new Thread(new HappyCustomer(cinema, seats1), "王五").start();
		new Thread(new HappyCustomer(cinema, seats1), "赵六").start();

	}
}
class HappyCustomer implements Runnable{

	Cinema cinema;
	List<Integer> seats;

	public HappyCustomer(Cinema cinema, List<Integer> seats) {
		this.cinema = cinema;
		this.seats = seats;
	}

	@Override
	public void run() {
		synchronized (cinema){
			boolean flag = cinema.bookTickets(seats);
			if (flag){
				System.out.println("出票成功" + Thread.currentThread().getName() + "，已经订购位置数为：" + seats);
			} else {
				System.out.println("出票失败" + Thread.currentThread().getName() + "，位置数不够：" + seats);
			}
		}
	}
}
class Cinema{
	List<Integer> available; // 可用位置
	String name; // 名称

	public Cinema(List<Integer> available, String name) {
		this.available = available;
		this.name = name;
	}

	public boolean bookTickets(List<Integer> seats){
		System.out.println("可用位置为：" + available);
		List<Integer> copy = new ArrayList<>();
		copy.addAll(available);
		// 减去
		copy.removeAll(seats);
		// 判断大小
		if (available.size() - copy.size() != seats.size()){
			return false;
		}
		available = copy;
		return true;
	}
}
