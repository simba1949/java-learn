package top.simba1949.assist;

import javassist.*;

import java.io.IOException;

/**
 * @author SIMBA1949
 * @date 2019/6/2 8:17
 */
public class JavassistLearn {
	public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException {
		// 获取类池
		ClassPool classPool = ClassPool.getDefault();
		// 创建类
		CtClass ctClass = classPool.makeClass("top.simba1949.assist.EmpGenerate");
		// 创建接口
		CtClass anInterface = classPool.makeInterface("java.lang.Cloneable");
		// 向类中添加接口
		ctClass.setInterfaces(new CtClass[]{anInterface});

		// 创建属性
		CtField empno = CtField.make("private int empno;", ctClass);
		CtField ename = CtField.make("private String ename;", ctClass);
		// 向类中添加属性，可以初始化属性值
		ctClass.addField(empno);
		ctClass.addField(ename, "null");

		// 创建方法
		CtMethod getEmpno = CtMethod.make("public int getEmpno() { return empno;}", ctClass);
		CtMethod setEmpno = CtMethod.make("public void setEmpno(int empno) {this.empno = empno;}", ctClass);
		// 向类中添加方法
		ctClass.addMethod(getEmpno);
		ctClass.addMethod(setEmpno);
		
		// 创建构造器
		// 无参构造
		CtConstructor ctConstructorNoParams = new CtConstructor(new CtClass[]{}, ctClass);
		ctClass.addConstructor(ctConstructorNoParams);
		
		// 有参构造，基本类型使用 CtClass 中的静态变量，引用类型使用  classPool.get("java.lang.String")
		CtConstructor ctConstructorParams = new CtConstructor(new CtClass[]{
				CtClass.intType, classPool.get("java.lang.String")
		}, ctClass);
		// 构造有参构造的方法体,$1 表示形参列表中第一个参数，$2 表示形参列表中第二个参数
		ctConstructorParams.setBody("{this.empno = $1; this.ename = $2;}");
		ctClass.addConstructor(ctConstructorParams);
		
		// 构造的类的字节码输出目录
		ctClass.writeFile("T:/IDE/IDEA/Workspace/java-learn/java-reflect/src/main/java");
	}
}