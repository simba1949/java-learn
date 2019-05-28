package top.simba1949.design;

/**
 * 单例模式：
 * 1.私有化构造方法，避免外部 new 对象
 * 2.提供私有静态属性，用于存储对象地址
 * 3.提供对外访问方法，获取属性（对象的地址）
 *
 * 使用 volatile 关键字防止 new 对象是指令重排，导致对象为 null
 * new 对象分三步：1. 开辟工作空间 2. 初始化对象信息 3. 返回对象地址给引用
 * 如果初始化对象信息过于耗时，步骤三优先于步骤二执行，从而导致对象为属性为空
 * @author SIMBA1949
 * @date 2019/5/25 12:29
 */
public class SingleInstance {
	private volatile static SingleInstance instance;
	private SingleInstance() {
	}

	public static SingleInstance getInstance(){
		if (null != instance){
			return instance;
		}
		synchronized (SingleInstance.class){
			if (null == instance){
				instance = new SingleInstance();
			}
		}

		return instance;
	}
}
