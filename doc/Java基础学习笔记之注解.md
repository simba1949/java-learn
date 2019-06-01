# Java基础学习笔记之注解

## 前言

注解需要结合反射功能使用才有意义

## 元注解

### @Target

定义注解的作用目标

#### 源码

```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Target {
    ElementType[] value();
}
```

ElementType

```java
public enum ElementType {
    TYPE, // 接口、类、枚举、注解
    FIELD, // 字段、枚举的常量
    METHOD, // 方法
    PARAMETER, // 方法参数
    CONSTRUCTOR, // 构造方法
    LOCAL_VARIABLE, // 局部变量
    ANNOTATION_TYPE, // 注解
    PACKAGE, // 包
    TYPE_PARAMETER, // 
    TYPE_USE //
}
```

### @Retention

定义注解的保留策略

#### 源码

```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Retention {
    RetentionPolicy value();
}
```

RetentionPolicy

```java
public enum RetentionPolicy {
    SOURCE, // 注解仅存在源码中
    CLASS, // 默认的保留策略，注解在class字节码文件中存在，但运行时无法或得
    RUNTIME // 注解会在class字节码中存在，在运行时可以通过反射获取到
}
```

### @Document

将此注解包含在 Javadoc 中

#### 源码

```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Documented {
}
```

### @Inherited

允许子类继承父类注解

#### 源码

```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Inherited {
}
```

## 自定义注解

使用 @Interface

```java
package top.simba1949.reflecation.user;

import java.lang.annotation.*;

/**
 * @author SIMBA1949
 * @date 2019/5/31 9:57
 */
@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Data {
}
```