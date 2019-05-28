package top.simba1949.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 任务调度：借助 Timer 和 TimerTask
 *
 * @author SIMBA1949
 * @date 2019/5/25 11:30
 */
public class MyTimer {
	public static void main(String[] args) {
		Timer timer = new Timer();
		// 执行安排
		// 10000 毫秒后执行任务一次
//		 timer.schedule(new MyTask(), 10000);
		// 10000 毫秒后执行，每个1000 毫秒后重复执行
//		timer.schedule(new MyTask(), 10000, 1000);
		// 指定日期时间后执行一次，每隔 3000 毫秒后重复执行
//		timer.schedule(new MyTask(),new Date(1558756320000L), 3000);
	}
}

/**
 * 继承 TimerTask 抽象类
 */
class MyTask extends TimerTask {

	@Override
	public void run() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(dateFormat.format(new Date()) + "天道酬勤");
	}
}
