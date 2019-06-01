package top.simba1949.reflecation.user;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

/**
 * @author SIMBA1949
 * @date 2019/6/1 8:34
 */
public class App {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
		Class<?> clazz = Class.forName("top.simba1949.reflecation.user.User");

		// 获取类所在的包信息
		Package aPackage = clazz.getPackage();

		// 获取全限定名
		String name = clazz.getName();
		// 获取类名
		String simpleName = clazz.getSimpleName();

		// 获取所有公开的属性
		Field[] fields = clazz.getFields();
		// 获取公开的指定属性，如果公开的没有会报错
//		Field username = clazz.getField("username");
		// 获取所有属性(包括私有和公有)
		Field[] declaredFields = clazz.getDeclaredFields();
		// 获取类中指定的属性（无论这个属性是公开的还是私有的，只有存在都可以获取）
		Field declaredUsername = clazz.getDeclaredField("username");


		// 获取所有公开的构造方法
		Constructor<?>[] constructors = clazz.getConstructors();
		// 获取指定形参列表的公开构造方法
		Constructor<?> constructor = clazz.getConstructor(String.class);
		// 获取所有的构造方法，包括公开的和私有的
		Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
		// 获取指定形参列表的构造方法，这个构造方法可以是公开的，也可以是私有的
		Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(String.class);

		// 获取所有公开的方法
		Method[] methods = clazz.getMethods();
		// 获取指定的公开方法，由于 Java 重载特性，需要指定方法名，以及方法中形参对应的类型对象才能获取，无参传 null，公开方法不存在会报异常
		Method method = clazz.getMethod("dance", String.class);
		// 获取所有的方法，不论是公开的还是私有的方法
		Method[] declaredMethods = clazz.getDeclaredMethods();
		Method declaredMethod = clazz.getDeclaredMethod("dance", null);
		Method enclosingMethod = clazz.getEnclosingMethod();

		Constructor<?> noParamsConstructor = clazz.getDeclaredConstructor(null);
		// 反射条用无参构造
		User noParamsUser = (User) noParamsConstructor.newInstance();
		Constructor<?> paramsConstructor = clazz.getDeclaredConstructor(int.class, String.class, Date.class, List.class);
		List<Role> roles = new ArrayList<>();
		User paramsUser = (User) paramsConstructor.newInstance(1, "李白", new Date(), roles);

		// 反射调用普通方法
		User user = (User) clazz.getDeclaredConstructor(null).newInstance();
		Method setUsername = clazz.getDeclaredMethod("setUsername", String.class);
		setUsername.invoke(user, "李白");
		System.out.println(user.getUsername());

		User userForFiled = (User) clazz.getDeclaredConstructor(null).newInstance();
		Field usernameField = clazz.getDeclaredField("username");
		// 设置访问权限
		usernameField.setAccessible(true);
		usernameField.set(userForFiled, "杜甫");

		// 获取所有公开的注解
		Annotation[] annotations = clazz.getAnnotations();
		// 获取指定的公开注解
		Data annotation1 = clazz.getAnnotation(Data.class);
		// 获取类上的所有注解，包括私有的和公开的
		Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();
		// 获取类上指定的注解，可以是私有的，也可以是公开的注解
		Data declaredAnnotation = clazz.getDeclaredAnnotation(Data.class);

		// 获取属性上的注解
		Field usernameFieldForAnnotation = clazz.getDeclaredField("username");
		Data annotation = usernameFieldForAnnotation.getAnnotation(Data.class);

		// 获取方法上的注解
		Method setIdMethod = clazz.getDeclaredMethod("setId", int.class);
		// 获取方法上抛出的异常
		Type[] genericExceptionTypes = setIdMethod.getGenericExceptionTypes();
		Class<?>[] exceptionTypes = setIdMethod.getExceptionTypes();

		// 获取形参列表数目
		int parameterCount = setIdMethod.getParameterCount();
		// 获取形参列表参数信息
		Parameter[] parameters = setIdMethod.getParameters();
		// 获取形参列表 Class 对象
		Class<?>[] parameterTypes = setIdMethod.getParameterTypes();

		// 获取方法返回参数类型
		Class<?> returnType = setIdMethod.getReturnType();

		Data setIdMethodAnnotation = setIdMethod.getAnnotation(Data.class);
		System.out.println("");


		Method testMethod = App.class.getDeclaredMethod("test", Map.class, List.class, Set.class);
		// 获取指定方法形参泛型信息
		Type[] genericParameterTypes = testMethod.getGenericParameterTypes();
		//
		for (Type genericParameterType : genericParameterTypes) {
			System.out.println(genericParameterType);
			//
			if (genericParameterType instanceof ParameterizedType){
				Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
				// 获取泛型中的信息
				for (Type actualTypeArgument : actualTypeArguments) {
					System.out.println("泛型类型" + actualTypeArgument);
				}
			}
		}

	}


	public Map<String, User> test(Map<String, User> map, List<User> list, Set<String> set){
		return null;
	}
}
