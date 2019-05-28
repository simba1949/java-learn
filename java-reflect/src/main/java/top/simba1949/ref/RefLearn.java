package top.simba1949.ref;

import java.lang.reflect.InvocationTargetException;

/**
 * @author SIMBA1949
 * @date 2019/5/27 16:39
 */
public class RefLearn {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Phone phone = new Phone();
		Class aClass = phone.getClass();
		Class<Phone> cl = Phone.class;
		Class forName = Class.forName("top.simba1949.ref.Phone");

	}
}
class Phone{

}
