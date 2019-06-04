## Java基础学习笔记之字节码操作

## 前言

官网：<http://www.javassist.org/>

maven地址：<https://mvnrepository.com/artifact/org.javassist/javassist>

版本：[3.25.0-GA](https://mvnrepository.com/artifact/org.javassist/javassist/3.25.0-GA)

## Java 动态性

### Java 动态性的两种常见实现方式

* 字节码操作
* 反射

### 运行时操作字节码可以实现的功能

* 动态生成新的类
* 动态改变某个类的结构（添加/删除/修改 新的属性/方法）

### 字节码操作优势

* 比反射开销小，性能高
* Javassist 性能高高于反射，低于 ASM

## 常见的字节码操作类库

* BCEL（Byte Code Engineering Library）
* ASM
* CGLIB（Code Generation Library）
* Javassist（常用提供 源码级别和字节码级别 操作）

> BCEL、ASM 因为基于 JVM 指令，所以效率高
>
> CGLIB、Javassist 性能低于 BCEL、ASM ，但是 Javassist 使用简单

## Javassist 库常用对象

CtClass

CtField

CtMethod

### 初步体验

```java
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
```

生成结果如下

```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package top.simba1949.assist;

public class EmpGenerate implements Cloneable {
    private int empno;
    private String ename = null;

    public int getEmpno() {
        return this.empno;
    }

    public void setEmpno(int var1) {
        this.empno = var1;
    }

    public EmpGenerate() {
    }

    public EmpGenerate(int var1, String var2) {
        this.empno = var1;
        this.ename = var2;
    }
}
```

### 获取类信息

#### 类名相关

```java
ClassPool classPool = ClassPool.getDefault();
CtClass ctClass = classPool.get("top.simba1949.assist.Emp");

// 获取生成类的全限定名
String name = ctClass.getName();
// 获取类名
String simpleName = ctClass.getSimpleName();
// 获取父类
CtClass superclass = ctClass.getSuperclass();
// 获取接口
CtClass[] interfaces = ctClass.getInterfaces();
```

#### 属性操作

```java
ClassPool classPool = ClassPool.getDefault();
CtClass ctClass = classPool.get("top.simba1949.assist.Emp");

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
```

#### 添加新方法

```java
ClassPool classPool = ClassPool.getDefault();
CtClass ctClass = classPool.get("top.simba1949.assist.Emp");

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
```

#### 在原方法基础上添加新功能

```java
ClassPool classPool = ClassPool.getDefault();
CtClass ctClass = classPool.get("top.simba1949.assist.Emp");

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
```

