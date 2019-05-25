# 多线程

## 程序、进程与线程

> 

## 线程优先级

> 线程优先级分配，优先级高的线程只是获取CPU使用权的概率高，不代表优先级低的线程不执行。
>
> 线程优先级设置需要在调用 start 方法之前设置。
>
> ```
> Thread thread = new Thread();
> // MIN_PRIORITY = 1; NORM_PRIORITY = 5; MAX_PRIORITY = 10;
> thread.setPriority(Thread.NORM_PRIORITY);
> ```

## 实现多线程的三种方式

1. 继承 Thread 类，重写 run 方法，使用 start() 方法开启新线程

   ```java
   public class ExtendThreadLearn extends Thread {
   
       @Override
       public void run() {
           System.out.println(Thread.currentThread().getName() + "多线程实现方式一，继承 Thread， 重写run方法");
       }
   }
   
   class ExtendThreadLearnTest {
       public static void main(String[] args) {
           ExtendThreadLearn td = new ExtendThreadLearn();
           td.start();
       }
   }
   ```

2. 实现 Runnable 接口，重写 run 方法，创建 Thread(Runnable) 对象，使用 Thread 的 start() 方法开启新线程

   ```java
   public class ImplRunnableLearn implements Runnable {
       public void run() {
           System.out.println(Thread.currentThread().getName() + "多线程实现方式二，实现 Runnable 接口，重写run方法");
       }
   }
   
   class ImplRunnableLearnTest {
       public static void main(String[] args) {
           Runnable runnable = new ImplRunnableLearn();
           new Thread(runnable).start();
       }
   }
   ```

3. 实现 Callable 接口（j.u.c包下），基于线程池

   ```java
   public class ImplCallable implements Callable<Object> {
       /**
   	 * 实现 Callable 接口，重写 call 方法
   	 * @return
   	 * @throws Exception
   	 */
       public Object call() throws Exception {
           System.out.println(Thread.currentThread().getName() + "多线程实现方式三：实现 Callable 接口");
           return "SUCCESS";
       }
   }
   
   class ImplCallableTest{
       public static void main(String[] args) throws ExecutionException, InterruptedException {
           // 创建实现 Callable 接口的对象
           ImplCallable implCallable = new ImplCallable();
           
           // 创建一个线程数为 3 的线程池
           ExecutorService pool = Executors.newFixedThreadPool(3);
           // 提交执行
           Future<Object> result = pool.submit(implCallable);
           // 获取执行结果
           Object o = result.get();
           System.out.println(o.toString());
           // 关闭服务
           pool.shutdown();
       }
   }
   ```

## start 方法调用

> 调用 start 方法并不一定立即执行，有系统分配调度执行
>
> 调用 run 方法，只是相当于调用普通方法，没有开启多线程

## 线程状态

![多线程](img/多线程.png)

* 新生状态

  > new Thread() 创建线程，该线程进入新生状态

* 就绪状态

  > 1. 新创建的线程调用 start 方法，该线程进入就绪状态
  > 2. 阻塞状态接触，该线程进入就绪状态
  > 3. 调用 yield 方法，该线程进入就绪状态
  > 4. JVM 切换到其他线程，该线程进入就绪状态

* 运行状态

  > 该线程获取 CPU 使用权，该线程进入运行状态

* 死亡状态

  > 线程执行完成，或者中断，该线程进入死亡状态

* 阻塞状态

  > 1. 调用 Thread.Sleep() 方法，该线程进入阻塞状态
  > 2. 调用 Object.wait() 方法，该线程进入阻塞状态
  > 3. 调用 join 方法，该线程进入阻塞状态
  > 4. io读写操作时，该线程进入阻塞状态

  ## 线程停止

  1. 线程中方法执行完成
  2. 通过变量控制线程停止

  Sleep

  > sleep(时间)，指定当前线程阻塞的毫秒数
  >
  > sleep 存在 InterruptedException 异常
  >
  > sleep 时间达到后，线程进入就绪状态
  >
  > sleep 可以模拟网络延时，倒计时等（模拟网络延时，放大发生问题的可能性）

  yield

  > 执行由运行状态进入就绪状态

  join

  > join 合并线程，等待此线程执行完成后，再执行其他线程，其他线程进入阻塞状态
  
  ## 线程分类
  
  > JVM 等所有的用户线程执行完成后才会停止
  >
  > ```
  > // 设置线程为守护线程
  > Thread thread = new Thread();
  > thread.setDaemon(true);
  > ```
  
  * 用户线程
  * 守护线程

## Synchornized

> 锁的范围太大，效率低下。锁的范围太小，没有保证线程安全。
>
> Synchornized 锁的资源是 this， 是对象的资源，不是锁的方法或者代码块。使用 Synchornized  要锁住要锁的对象，否则也会导致线程不安全。Synchornized 锁的对象地址不能变，属性可以变。
>
> 双重检查，要考虑临界值情况。

### 两种用法

* 方法级别

  ```java
  public synchronized void syncFun(){
  
  } 
  ```

* 代码块（推荐使用）

  ```java
  synchronized (cinema){
      // 需要同步的代码
}
  ```
  

## 并发容器

* CopyOnWriteArrayList
* CopyOnWriteArraySet

## 锁

### 死锁

> 多个线程各自占有一些共享资源，并且等待其他线程占有的资源释放才能进行，而导致两个或者多个线程都在等待对方释放资源，都停止执行的情形。
>
> 某个同步块拥有“两个以上的对象锁”时，就可能发生死锁。

### 可重入锁

锁作为并发共享数据保证一致性的工具，大多数内置锁都是可重入的，也就是说，如果某个线程试图获取一个已经由它自己持有的锁时，那么这个请求会立刻成功，并且会将这个锁的计数值加 1 ，而当线程退出同步代码块是，计数器将会递减，当计数值等于 0 时，锁释放。如果没有可重入锁的支持，在第二次企图获取锁时将会进入死锁状态。

```
# j.u.c 下的可重入锁
ReentrantLock
```

锁分为两类：

* 悲观锁

  >synchronized 是独占锁即悲观锁，会导致其他所有需要锁的线程挂起，等待持有锁的线程释放锁

* 乐观锁

  > 每次不加锁，而是假设没有冲突而去完成某项操作，如果因为冲突失败就重试，直到成功为止。
  >
  > CAS 是乐观锁的一种实现。
  >
  > Java 中 CAS 类：java.util.concurrent.atomic 包下的类

## 线程通信

### 一、管程法

并发写作模型“生产者/消费者模式”——>管程法

* 生产者：负责生产数据的模块（模块可能是：方法、对象、线程、进程）
* 消费者：负责处理数据的模块（模块可能是：方法、对象、线程、进程）
* 缓冲区：消费者不能直接使用生产者的数据，他们之间有个“缓冲区”

### 二、信号灯法

并发写作模型“生产者/消费者模式”——>信号灯法



### 通信方法

Java 提供了3个方法解决线程之间通信问题

|            方法名             |                             作用                             |
| :---------------------------: | :----------------------------------------------------------: |
|       final void wait()       | 表示线程一致等待，直到其他线程通知，与 Thread.sleep() 不同，会释放锁 |
| final void wait(long timeout) |                        指定等待毫秒数                        |
|      final void notify()      |                  唤醒一个处于等待状态的线程                  |
|    final void notifyAll()     | 唤醒同一个对象上所有调用 wait() 方法的线程，优先级高的线程优先调度 |

> 注：以上方法只能防止在**同步方法**或者**同步块**中，否者会报异常

## HappenBefore

> 代码执行顺序没有安装期望执行，是因为编译器和CPU会尝试重排指令使得代码更快的运行。
>
> 通常两端代码没有任何联系，不互相影响。

1. 虚拟机层面
2. 硬件层面

## Volatile

> volatile 保证线程间变量的可见性，简单地说就是当线程A对变量X进行修改之后，在线程A后面执行的其他线程能够看到变量X的变动，更详细的说是要符合以下两个规则：
>
> 1. 线程对变量进行修改之后，要立刻会写主内存
> 2. 线程对变量读取的时候，要从主内存中读取，而不是缓存

volatile 是不错的机制，但是 valatile 不能保证原子性，volatile 只保证数据的同步，可以避免指令重排

## ThreadLocal

### InheritableThreadLocal

继承上下文环境的数据，拷贝一份数据到子线程，共享一次，改变不影响父线程

































