# Java基础学习笔记之反射

## 获取字节码文件的三种方式

**注：一个类的 Class 对象在 JVM 中只存在一个，无论获取多少次，地址值相等**

**数组中维度不同在 JVM 中地址值不同；维度相同，长度无论相同与否，在 JVM 中地址值相等**

```
// 使用 Class 类静态方法
Class clazz1 = Class.forName("top.simba1949.ref.Phone");
// 通过对象名.class 获取
Class<Phone> clazz2 = Phone.class;
// 通过实例对象.getClass() 获取
Phone phone = new Phone();
Class clazz3 = phone.getClass();
```

## 通过反射创建对象

```
Class clazz1 = Class.forName("top.simba1949.ref.Phone");

// 获取无参构造方法，创建对象（推荐）
Phone phone1 = (Phone) clazz1.getConstructor().newInstance();
// 获取有参构造方法，创建对象（推荐）
Phone phone2 = (Phone) clazz1.getConstructor(int.class).newInstance(1);
// 直接通过创建（不推荐）
Phone phone3 = (Phone) clazz1.newInstance();
```

## 通过反射获取类中信息

### 获取类信息

```java
Class<?> clazz = Class.forName("top.simba1949.reflecation.user.User");
```

#### 获取类所在的包信息

```java
// 获取类所在的包信息
Package aPackage = clazz.getPackage();
```

#### 获取类的名称

```java
// 获取全限定名
String name = clazz.getName();
// 获取类名
String simpleName = clazz.getSimpleName();
```

#### 获取属性信息

```java
// 获取所有公开的属性
Field[] fields = clazz.getFields();
// 获取公开的指定属性，如果公开的属性不存在，则会报异常
Field username = clazz.getField("username");
// 获取所有属性(包括私有和公有)
Field[] declaredFields = clazz.getDeclaredFields();
// 获取类中指定的属性（无论这个属性是公开的还是私有的，只有存在都可以获取）
Field declaredUsername = clazz.getDeclaredField("username");
```

#### 获取构造方法

```java
// 获取所有公开的构造方法
Constructor<?>[] constructors = clazz.getConstructors();
// 获取指定形参列表的公开构造方法，形参列表为 null 时获取的是无参的公开的构造方法
Constructor<?> constructor = clazz.getConstructor(String.class);
// 获取所有的构造方法，包括公开的和私有的
Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
// 获取指定形参列表的构造方法，这个构造方法可以是公开的，也可以是私有的，形参列表为 null 时获取的是无参构造
Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(String.class);
```

#### 获取方法

```java
// 获取所有公开的方法
Method[] methods = clazz.getMethods();
// 获取指定的公开方法，由于 Java 重载特性，需要指定方法名，以及方法中形参对应的类型对象才能获取，无参传 null，公开方法不存在会报异常
Method method = clazz.getMethod("dance", String.class);
// 获取所有的方法，不论是公开的还是私有的方法
Method[] declaredMethods = clazz.getDeclaredMethods();
Method declaredMethod = clazz.getDeclaredMethod("dance", null);
```

#### 获取方法上的形参列表

```java
Method setIdMethod = clazz.getDeclaredMethod("setId", int.class);
// 获取形参列表数目
int parameterCount = setIdMethod.getParameterCount();
// 获取形参列表参数信息
Parameter[] parameters = setIdMethod.getParameters();
// 获取形参列表 Class 对象
Class<?>[] parameterTypes = setIdMethod.getParameterTypes();
```

#### 获取方法上抛出的异常

```java
Method setIdMethod = clazz.getDeclaredMethod("setId", int.class);
// 获取方法上抛出的异常
Type[] genericExceptionTypes = setIdMethod.getGenericExceptionTypes();
Class<?>[] exceptionTypes = setIdMethod.getExceptionTypes();
```

#### 获取方法上的返回参数

```java
// 获取方法返回参数类型
Class<?> returnType = setIdMethod.getReturnType();
```

#### 获取方法中形参列表的泛型和返回值的泛型

> 为了通过反射操作泛型，Java 新增 ParameterizedType / GenericArrayType / TypeVariable / WildcardType 几种类型来代表不能被归一到 Class 类中的类型，但是又和原始类型齐名的类型
>
> * ParameterizedType : 表示一种参数化的类型，比如 Collection\<String>
> * GenericArrayType : 表示一种元素类型是参数化类型或者类型变量的数组类型
> * TypeVariable : 是各种类型变量的公共父接口
> * WildcardType :代表一种通配符类型的表达式

```java
Method testMethod = App.class.getDeclaredMethod("test", Map.class, List.class, Set.class);
// 获取指定方法形参泛型信息
Type[] genericParameterTypes = testMethod.getGenericParameterTypes();
for (Type genericParameterType : genericParameterTypes) {
    System.out.println(genericParameterType);
    //
    if (genericParameterType instanceof ParameterizedType){
        Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
        // 获取泛型中的对象类型
        for (Type actualTypeArgument : actualTypeArguments) {
            System.out.println("泛型类型" + actualTypeArgument);
        }
    }
}
```

#### 获取类上注解

```java
// 获取所有公开的注解
Annotation[] annotations = clazz.getAnnotations();
// 获取指定的公开注解
Data annotation1 = clazz.getAnnotation(Data.class);
// 获取类上的所有注解，包括私有的和公开的
Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();
// 获取类上指定的注解，可以是私有的，也可以是公开的注解
Data declaredAnnotation = clazz.getDeclaredAnnotation(Data.class);
```

#### 获取属性上的注解

```java
// 获取属性上的注解
Field usernameFieldForAnnotation = clazz.getDeclaredField("username");
Data annotation = usernameFieldForAnnotation.getAnnotation(Data.class);
```

#### 获取方法上的注解

```java
// 获取方法上的注解
Method setIdMethod = clazz.getDeclaredMethod("setId", int.class);
Data setIdMethodAnnotation = setIdMethod.getAnnotation(Data.class);
```

### 反射调用

#### 反射调用构造方法

```java
Constructor<?> noParamsConstructor = clazz.getDeclaredConstructor(null);
// 反射调用无参构造
User noParamsUser = (User) noParamsConstructor.newInstance();
// 反射调用有参构造
Constructor<?> paramsConstructor = clazz.getDeclaredConstructor(int.class, String.class, Date.class, List.class);
List<Role> roles = new ArrayList<>();
User paramsUser = (User) paramsConstructor.newInstance(1, "李白", new Date(), roles);
```

#### 反射调用普通方法

```java
// 反射调用普通方法
User user = (User) clazz.getDeclaredConstructor(null).newInstance();
Method setUsername = clazz.getDeclaredMethod("setUsername", String.class);
// 执行方法
setUsername.invoke(user, "李白");
System.out.println(user.getUsername());
```

#### 反射操作属性

```java
User userForFiled = (User) clazz.getDeclaredConstructor(null).newInstance();
Field usernameField = clazz.getDeclaredField("username");
// 设置访问权限，设置为true，取消 Java 语言访问检查，可以直接访问，提高效率
usernameField.setAccessible(true);
// 设置属性值
usernameField.set(userForFiled, "杜甫");
```













