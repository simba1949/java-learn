package top.simba1949.assist;

import javassist.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author SIMBA1949
 * @date 2019/6/2 9:12
 */
public class JavassistLearn02 {
	public static void main(String[] args) throws NotFoundException, CannotCompileException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException {
		ClassPool classPool = ClassPool.getDefault();
		CtClass ctClass = classPool.get("top.simba1949.assist.Emp");

//		getClassInfo(ctClass);

//		addMethod(classPool, ctClass);

//		modifyMethod(ctClass);

		modifyField(ctClass);
	}


	public static void getClassInfo(CtClass ctClass) throws NotFoundException {
		// 获取生成类的全限定名
		String name = ctClass.getName();
		// 获取类名
		String simpleName = ctClass.getSimpleName();
		// 获取父类
		CtClass superclass = ctClass.getSuperclass();
		// 获取接口
		CtClass[] interfaces = ctClass.getInterfaces();

		System.out.println("name=" + name + "&simpleName=" + simpleName + "&superclass=" + superclass);
	}

	public static void addMethod(ClassPool classPool,CtClass ctClass) throws CannotCompileException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NotFoundException {
		// 创建新方法方法
		// 第一个参数是返回值类型，第二个参数是方法名
		// 第三个参数是形参类型，第四个参数是值哪个类
		CtMethod addMethod = new CtMethod(CtClass.intType, "add", new CtClass[]{CtClass.intType, classPool.get("java.lang.String")}, ctClass);
		// 设置方法修饰符
		addMethod.setModifiers(Modifier.PUBLIC);
		// 设置方法体
		addMethod.setBody("{System.out.println($2); return $1 + 2;}");
		// 将方法添加到类中
		ctClass.addMethod(addMethod);


		// 通过反射调用新生方法
		// 将 Javassist 生成的 CtClass 类转换成 Java 中 Class 类
		Class<?> aClass = ctClass.toClass();
		Method add = aClass.getDeclaredMethod("add", int.class, String.class);
		add.setAccessible(true);

		Emp emp = (Emp) aClass.getConstructor().newInstance();
		Object result = add.invoke(emp,1, "君不见黄河之水天上来");
		System.out.println("result=" + result);
	}

	public static void modifyMethod(CtClass ctClass) throws NotFoundException, CannotCompileException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		// 在原有的方法基础上添加新功能
		// 获取方法
		CtMethod sayHello = ctClass.getDeclaredMethod("sayHello");
		// 在方法执行之前执行
		sayHello.insertBefore("System.out.println(\"在方法执行之前执行\");");
		// 在某行添加代码
		sayHello.insertAt(23, "System.out.println(\"在23行添加代码，用打印标识\");");
		//在方法执行之后执行
		sayHello.insertAfter("System.out.println(\"在方法执行之后执行\");");

		// 通过反射调用新生方法
		// 将 Javassist 生成的 CtClass 类转换成 Java 中 Class 类
		Class<?> aClass = ctClass.toClass();
		Emp emp = (Emp) aClass.getConstructor().newInstance();
		emp.sayHello();
	}

	public static void modifyField(CtClass ctClass) throws CannotCompileException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
		// 创建属性，第一个参数是类型，第二参数是名称， 第三个参数指定哪个CT类
		CtField ctField = new CtField(CtClass.intType, "id", ctClass);
		// 设置属性修饰符
		ctField.setModifiers(Modifier.PRIVATE);
		// 将属性添加到类中，可以初始化值
		ctClass.addField(ctField, "0");

		Class<?> aClass = ctClass.toClass();
		Field idField = aClass.getDeclaredField("id");
		idField.setAccessible(true);
		Emp emp = (Emp) aClass.getConstructor().newInstance();
		Object result = idField.get(emp);
		System.out.println("result=" + result);
	}
}
