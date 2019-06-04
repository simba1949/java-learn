package top.simba1949;

/**
 * @author SIMBA1949
 * @date 2019/6/4 21:29
 */
public class ClassLoaderLearn {
	public static void main(String[] args) {
		new Son();
	}
}

class Father{
	static {
		System.out.println("父类静态域");
	}
	{
		System.out.println("父类普通域");
	}
	public Father() {
		System.out.println("父类构造方法");
	}
}

class Son extends Father{
	static {
		System.out.println("子类静态域");
	}
	{
		System.out.println("子类普通域");
	}

	public Son() {
		System.out.println("子类构造方法");
	}
}
