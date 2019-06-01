package top.simba1949.ref;

import java.lang.reflect.InvocationTargetException;

/**
 * @author SIMBA1949
 * @date 2019/5/27 16:39
 */
public class RefLearn {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Phone phone = new Phone();
		Class clazz1 = phone.getClass();
		Class<Phone> clazz2 = Phone.class;
		Class clazz3 = Class.forName("top.simba1949.ref.Phone");

		// 获取无参构造方法，创建对象（推荐）
		Phone phone1 = (Phone) clazz1.getConstructor().newInstance();
		// 获取有参构造方法，创建对象（推荐）
		Phone phone2 = (Phone) clazz1.getConstructor(int.class).newInstance(1);
		// 直接通过创建，不推荐
		Phone phone3 = (Phone) clazz1.newInstance();
		System.out.println();
	}
}
class Phone{

	private int id;

	public Phone() {
	}

	public Phone(int id) {
		this.id = id;
	}
}
