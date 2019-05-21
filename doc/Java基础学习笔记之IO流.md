# Java IO 流

## File 基础
### File 构造器
```java
// 通过全路径创建 File 对象
File fileOne = new File("T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/file.txt");
System.out.println("通过全路径创建 File 对象：" + fileOne.getName());

// 通过父路径 + 子文件或者文件夹路径 创建 File 对象
String parentStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file";
File fileTwo = new File(parentStr, "fileLearn.txt");
System.out.println("通过父路径 + 子文件或者文件夹路径 创建 File 对象：" + fileTwo.getName());

// 通过 父File 对象 + 子文件或者文件夹路径 创建 File 对象
File parentFile = new File("T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file");
File fileThree = new File(parentFile, "fileLearn.txt");
System.out.println("通过 父File 对象 + 子文件或者文件夹路径 创建 File 对象：" + fileThree.getName());
```
### 创建 File  对象
```java
try {
    // 创建文件
    File file = new File("T:\\IDE\\IDEA\\Workspace\\java-learn\\java-io\\src\\main\\resources\\file\\createdFile.md");
    file.createNewFile();
} catch (IOException e) {
    e.printStackTrace();
}

// 创建一级文件夹
File dir = new File("T:\\IDE\\IDEA\\Workspace\\java-learn\\java-io\\src\\main\\resources\\file\\newDir");
dir.mkdir();

// 创建多级文件夹
File dirs = new File("T:\\IDE\\IDEA\\Workspace\\java-learn\\java-io\\src\\main\\resources\\file\\oneDirs\\twoDirs");
dirs.mkdirs();
```
### File 常用方法
````java
File file = new File("T:\\IDE\\IDEA\\Workspace\\java-learn\\java-io\\src\\main\\resources\\file\\file2.md");
// 获取文件/文件夹名称
System.out.println("获取文件/文件夹名称：" + file.getName());
// 获取文件/文件夹的绝对路径
System.out.println("获取文件/文件夹的绝对路径：" + file.getAbsolutePath());
// 获取文件/文件夹的路径，可能是绝对路径，也可能是相对路径
System.out.println("获取文件/文件夹的路径，可能是绝对路径，也可能是相对路径：" + file.getPath());
// 获取文件/文件夹字节数
System.out.println("获取文件/文件夹字节数：" + file.length());
// 判断该 File 对象是不是文件
System.out.println("判断该 File 对象是不是文件：" + file.isFile());
// 判断该 File 对象是不是文件夹
System.out.println("判断该 File 对象是不是文件夹：" + file.isDirectory());
// 判断文件或者文件夹是否存在
System.out.println("判断文件或者文件夹是否存在：" + file.exists());
// 删除文件/文件夹
//		System.out.println("删除文件/文件夹是否成功" + file.delete());
// 重命名，如果路径相同只做改名，如果路径不同剪切并改名
//		System.out.println("重命名" + file.renameTo(new File("T:\\IDE\\IDEA\\Workspace\\java-learn\\java-io\\src\\main\\resources\\file\\file.txt")));
// 获取 File 对象下所有 File 对象，文件夹会列出来
File[] files = file.listFiles();
if (null != files){
    for (File item : files) {
        System.out.println("item.getName() = " + item.getName());
    }
}
````
## IO 基础

### IO 流操作步骤

```text
1. 选择数据源
2. 选择流
3. 对流进行操作，读或者写
4. 对流进行关闭，先开的流后关闭，后开的流先关闭
```
### 字符流和字节流的使用

```text
字符流不能操作音频、视频、图片，字符流一般仅当读取一行文本或者写入一行文本时使用
```
### 核心类

> 在整个 java.io 包中最重要的就是 5 个类和 3 个接口

| 类/接口      | 说明       |
| ------------ | ---------- |
| File         | 文件类     |
| InputStream  | 字节输入流 |
| OutputStream | 字节输出流 |
| Reader       | 字符输入流 |
| Writer       | 字符输出流 |
| Closeable    | 关闭流接口 |
| Flushable    | 刷新流接口 |
| Serializable | 序列号接口 |

### 四大抽象类

| 抽象类       | 说明                             | 常用方法                                      |
| ------------ | -------------------------------- | --------------------------------------------- |
| InputStream  | 字节输入流的父类，数据单位为字节 | int read(); void close()                      |
| OutputStream | 字节输出流的父类，数据单位为字节 | int write(int); void flush(); void close()    |
| Reader       | 字符输入流的父类，数据单位字符   | int read(); void close()                      |
| Writer       | 字符输出流的父类，数据单位字符   | int write(String); void flush(); void close() |

## IO 分类

![Java IO](C:/Users/Simba/Desktop/Java IO.png)

### 字节流(InputStream/OutputStream)

#### 文本字节流

##### FileInputStream

```JAVA
/**
 * 单字节读取数据
 */
public static void singleByteRead(){
    // 创建源
    String fileStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/file.txt";
    File file = new File(fileStr);
    // 选择流
    FileInputStream is = null;
    try {
        is = new FileInputStream(file);
        // 对流进行操作，每读取一个字节，将字节值赋值给 temp，读到没有值返回 -1
        int temp = -1;
        while ((temp = is.read()) != -1){
            System.out.print((char)temp);
        }
    } catch (IOException e){
        e.printStackTrace();
    } finally {
        if (null != is){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 使用自定义缓存区读取数据
 */
public static void bufferedRead(){
    // 创建源
    String fileStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/file.txt";
    File file = new File(fileStr);

    // 选择流
    FileInputStream is = null;
    try {
        is = new FileInputStream(file);
        byte[] flush = new byte[8];
        int len = -1;
        // 每次读取 flush.length 字节数，并存储在 flush 中
        while ((len = is.read(flush)) != -1){
            for (byte ele : flush) {
                System.out.print((char)ele);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (null != is){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

##### FileOutputStream

```java
public static void writer(){
    // 创建源
    String fileStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/osWriter.txt";
    File file = new File(fileStr);

    String msg = "I know that she's out there...the one I'm suppose to share my whole life with.\n";
    byte[] src = msg.getBytes();

    FileOutputStream os = null;
    try {
        // 选择流，写入是否追加，默认 false 表示重写， true 表示在文件后面追加
        os = new FileOutputStream(file, true);
        os.write(src, 0, src.length);
        os.flush();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e){
        e.printStackTrace();
    } finally {
        // 关闭流
        if (null != os){
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

##### FileInputStream & FileOutputStream

```java
public static void readAndWiter(){
    // 创建读取的源，和写入的源
    String srcStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/input.txt";
    File srcFile = new File(srcStr);
    String destStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/output.txt";
    File destFile = new File(destStr);

    // 选择流
    FileInputStream is = null;
    FileOutputStream os = null;

    try {
        is = new FileInputStream(srcFile);
        os = new FileOutputStream(destFile);

        byte[] flush = new byte[1024 * 8];
        int len = -1;
        while ((len = is.read(flush)) != -1){
            os.write(flush, 0 ,len);
        }
        os.flush();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e){
        e.printStackTrace();
    } finally {
        if (null != os){
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (null != is){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

#### 字节缓冲流（文本字节流的装饰者）

##### BufferedInputStream

```java
/**
 * 单字节读取
 */
public static void singleBufferedRead(){
    // 创建源
    String src = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/bufferedInput.txt";
    File file = new File(src);

    // 选择流
    BufferedInputStream is = null;
    try {
        is = new BufferedInputStream(new FileInputStream(file));
        // 操作流
        int temp = -1;
        while((temp = is.read()) != -1){
            System.out.print((char) temp);
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e){
        e.printStackTrace();
    } finally {
        // 关闭流
        if (null != is){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 使用自定义缓存区读取数据
 */
public static void flushBufferedRead(){
    // 创建源
    String srcStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/bufferedInput.txt";
    File file = new File(srcStr);

    BufferedInputStream is = null;
    try {
        // 选择流
        is = new BufferedInputStream(new FileInputStream(file));
        // 操作流
        byte[] flush = new byte[1024];
        int len = -1;
        while ((len = is.read(flush)) != -1){
            for (byte ele : flush) {
                System.out.print((char)ele);
            }
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e){
        e.printStackTrace();
    } finally {
        // 关闭流
        if (null != is){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

##### BufferedOutputStream

```java
public static void writer(){
    // 创建输出源
    String srcStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/bufferedOutput.txt";
    File file = new File(srcStr);
    // 创建输入源
    String msg = "When the darkness falls will you please shine her the way (shine he the way)";

    BufferedOutputStream os = null;
    try {
        // 选择流
        os = new BufferedOutputStream(new FileOutputStream(file));
        // 操作流
        os.write(msg.getBytes());
        os.flush();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e){
        e.printStackTrace();
    } finally {
        // 关闭流
        if (null != os){
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

##### BufferedInputStream & BufferedOutputStream

```java
public static void readAndWriter(){
    // 创建源
    String src = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/bufferedSrc.txt";
    File srcFile = new File(src);
    String dest = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/bufferedDest.txt";
    File destFile = new File(dest);

    // 选择流
    BufferedInputStream is = null;
    BufferedOutputStream os = null;

    try {
        is = new BufferedInputStream(new FileInputStream(srcFile));
        os = new BufferedOutputStream(new FileOutputStream(destFile));
        // 操作流
        byte[] flush = new byte[1024 * 8];
        int len = -1;
        while ((len = is.read(flush)) != -1){
            os.write(flush, 0, len);
        }
        os.flush();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e){
        e.printStackTrace();
    } finally {
        // 关闭流
        if (null != os){
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (null != is){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

#### 字节数组流
> 字节数组源可以看做是本地电脑的内存、远程服务器的内存、网络上的内存，Java 可以直接访问，流可以不用关闭，由 GC 进行关闭，关闭方法是空方法。任何东西都可以转换成字节数组。字节数组存储在内存，尽可能占用内存小。
>
> ByteArrayInputStream ：二进制——>字符
>
> ByteArrayOutputStream：字符——>二进制

##### ByteArrayInputStream

```java
/**
	 * 单字节读取
	 */
public static void read(){
    String src = "When the darkness falls will you please shine her the way (shine he the way)\n";
    // 创建源
    byte[] srcBytes = src.getBytes();

    // 选择流
    ByteArrayInputStream is = new ByteArrayInputStream(srcBytes);
    int temp = -1;
    while ((temp = is.read()) != -1){
        System.out.println((char)temp);
    }
    try {
        // 关闭流
        is.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

/**
	 * 自定义缓存读取数据
	 */
public static void flushRead(){
    String src = "Whenthe";
    // 创建源
    byte[] srcBytes = src.getBytes();

    // 选择流
    ByteArrayInputStream is = new ByteArrayInputStream(srcBytes);
    try {
        // 每次读取多少字节数，len 表示每次读取字节数的实际大小，内容最后一次读取完成后，还有进行一次读取，但是读取内容为空，返回-1
        byte[] flush = new byte[2];
        int len = -1;
        while ((len = is.read(flush)) != -1){
            System.out.println(new String(flush, 0, len));
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

##### ByteArrayOutputStream

```java
public static void writer(){
    String msg = "When the darkness falls will you please shine her the way (shine he the way)\n";
    // 创建源
    byte[] bytes = null;

    // 选择流
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    try {
        os.write(msg.getBytes());
        byte[] destSrc = os.toByteArray();
        String s = new String(destSrc, 0, destSrc.length);
        System.out.println(s);os.flush();
        os.flush();
    } catch (IOException e) {
        e.printStackTrace();
    }finally {
        if (null != os){
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

##### ByteArrayInputStream & ByteArrayOutputStream

```java
public static void readAndWrite(){
    // 创建源
    String srcStr = "God will you let her know that I love her so";
    byte[] src = srcStr.getBytes();

    // 选择流
    ByteArrayInputStream is = new ByteArrayInputStream(src);
    ByteArrayOutputStream os = new ByteArrayOutputStream();

    try{
        // 操作流
        byte[] flush = new byte[10];
        int len = -1;
        while ((len = is.read(flush)) != -1){
            // 每次读取 flush.length 字节数，但最终 os 拥有全部数据， os.toByteArray() 指的是 n 次读取数据的累加
            os.write(flush, 0, len);
            os.flush();
            byte[] bytes = os.toByteArray();
            System.out.println(new String(bytes, 0, bytes.length));
        }
    }catch (IOException e){
        e.printStackTrace();
    } finally {
        // 关闭流
        if (null != os){
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (null != is){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 字符流(Reader/Writer)

#### 文本字符流

##### FileReader

```java
/**
	 * 单字符读取
	 */
public static void singleCharReader(){
    // 创建源
    String srcStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/fileReader.txt";
    File srcFile = new File(srcStr);

    // 选择流
    FileReader reader = null;
    try {
        reader = new FileReader(srcFile);
        int temp;
        while ((temp = reader.read()) != -1){
            System.out.println("temp:" + temp + (char) temp);
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e){
        e.printStackTrace();
    } finally {
        if (null != reader){
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
	 * 自定义缓存读取
	 */
public static void flushRead(){
    // 创建源
    String srcStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/fileReader.txt";
    File file = new File(srcStr);

    // 选择流
    FileReader reader = null;
    try {
        reader = new FileReader(file);
        // 操作流
        char[] flush = new char[10];
        int len = -1;
        while ((len = reader.read(flush)) != -1){
            System.out.print(new String(flush,0, len));
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e){
        e.printStackTrace();
    } finally {
        // 关闭流
        if (null != reader){
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

##### FileWriter

```java
public static void write(){
    // 创建输出源
    String dest = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/fileWriter.txt";
    File destFile = new File(dest);
    // 字符
    String src = "天道酬勤，the time of show me code";

    // 选择流
    FileWriter writer = null;
    try {
        writer = new FileWriter(destFile);
        // 操作流
        writer.write(src, 0, src.length());
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

##### FileReader & FileWriter

```java
public static void readerAndWriter(){
    // 创建输入源
    String srcStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/readerAndWriterSrc.txt";
    File srcFile = new File(srcStr);
    // 创建输出源
    String destStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/readerAndWriterDest.txt";
    File destFile = new File(destStr);

    // 选择流
    FileReader reader = null;
    FileWriter writer = null;

    try {
        reader = new FileReader(srcFile);
        writer = new FileWriter(destFile);

        // 操作流
        char[] flush = new char[10];
        int len = -1;
        while ((len = reader.read(flush)) != -1){
            writer.write(flush, 0, len);
        }
        writer.flush();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e){
        e.printStackTrace();
    } finally {
        // 关闭流
        if (null != writer){
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (null != reader){
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

#### 字符缓冲流（文本字符流的装饰者）

##### BufferedReader

```java
/**
	 * 单个字符读取
	 */
public static void singleCharRead(){
    // 创建源
    String src  = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/bufferedReader.txt";
    File srcFile = new File(src);

    // 选择流
    BufferedReader reader = null;

    try {
        reader = new BufferedReader(new FileReader(srcFile));
        // 操作流
        int temp = -1;
        while ((temp = reader.read()) != -1){
            System.out.print((char)temp);
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e){
        e.printStackTrace();
    } finally {
        // 关闭流
        if (null != reader){
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
	 * 自定义缓存读取
	 */
public static void flushRead(){
    // 创建源
    String src = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/bufferedReader.txt";
    File srcFile = new File(src);

    // 选择流
    BufferedReader reader = null;
    try {
        reader = new BufferedReader(new FileReader(srcFile));
        char[] flush = new char[10];
        int len = -1;
        while ((len = reader.read(flush)) != -1){
            System.out.print(new String(flush, 0, len));
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e){
        e.printStackTrace();
    } finally {
        // 关闭流
        if (null != reader){
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

##### BufferedWriter

```java
public static void writer(){
    // 1.创建源
    String dest = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/bufferedWriter.txt";
    File destFile = new File(dest);
    // 2.选择流
    BufferedWriter writer = null;
    try {
        writer = new BufferedWriter(new FileWriter(destFile));
        String src = "dear god, 哦，我亲爱的上帝";
        // 3.操作流
        writer.write(src);
        writer.flush();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        // 4.关闭流
        if (null != writer){
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

##### BufferedReader & BufferedWriter

```java
public static void readAndWrite(){
    // 1.创建源
    String srcStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/bufferedReadAndWriterSrc.txt";
    File srcFile = new File(srcStr);
    String destSrc = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/bufferedReadAndWriterDest.txt";
    File destFile = new File(destSrc);
    // 2.选择流
    BufferedReader reader = null;
    BufferedWriter writer = null;

    try {
        reader = new BufferedReader(new FileReader(srcFile));
        writer = new BufferedWriter(new FileWriter(destFile));
        // 3.操作流
        char[] flush = new char[10];
        int len = -1;
        while ((len = reader.read(flush)) != -1){
            writer.write(flush, 0, len);
        }
        writer.flush();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e){
        e.printStackTrace();
    } finally {
        // 4.关闭流
        if (null != writer){
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (null != reader){
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 转换流

> 装饰流：字节流转换成字符流（处理纯文本内容，可以指定字符集，解码为字符）；字符流转换成字节流（可以指定字符集，对字符进行编码）

##### InputStreamReader

```java
/**
	 * 单字符读取
	 */
public static void singleRead(){
    // 1.创建源
    String srcStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/inputStreamReader.txt";
    File srcFile = new File(srcStr);
    // 2. 选择流
    InputStreamReader is = null;
    try {
        is = new InputStreamReader(new FileInputStream(srcFile));
        // 3.操作流
        int temp = -1;
        while ((temp = is.read()) != -1){
            System.out.print((char) temp);
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e){
        e.printStackTrace();
    } finally {
        if (null != is){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
	 * 自定义缓存读取
	 */
public static void flushRead(){
    // 1.创建源
    String srcStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/inputStreamReader.txt";
    File srcFiel = new File(srcStr);
    // 2.选择流
    InputStreamReader is = null;
    try {
        is = new InputStreamReader(new FileInputStream(srcFiel));
        // 3.操作流
        char[] flush = new char[10];
        int len = -1;
        while ((len = is.read(flush)) != -1){
            System.out.print(new String(flush, 0, len));
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e){
        e.printStackTrace();
    } finally {
        // 4.关闭流
        if (null != is){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

##### OutputStreamWriter

```java
public static void write(){
    // 1.创建源
    String destStr = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/OnputStreamWriter.txt";
    File destFile = new File(destStr);

    // 2.选择流
    OutputStreamWriter os = null;
    try {
        os = new OutputStreamWriter(new FileOutputStream(destFile));
        String msg = "把乾坤留在我心中的那一刻，就注定我不会寂寞";
        // 3.操作流
        os.write(msg, 0, msg.length());
        os.flush();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e){

    } finally {
        // 4.关闭流
        if (null != os){
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

##### InputStreamReader & OutputStreamWriter

```java
public static void readAndWrite(){
    // 1.创建源
    String srcPath = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/InputAndOutputStreamReadAndWriterSrc.txt";
    File srcFile = new File(srcPath);
    String destPath = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/InputAndOutputStreamReadAndWriterDest.txt";
    File destFile = new File(destPath);
    // 2.选择流
    InputStreamReader is = null;
    OutputStreamWriter os = null;

    try {
        is = new InputStreamReader(new FileInputStream(srcFile));
        os = new OutputStreamWriter(new FileOutputStream(destFile));
        // 3.操作流
        char[] flush = new char[10];
        int len = -1;
        while ((len = is.read(flush)) != -1){
            os.write(flush, 0, len);
        }
        os.flush();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e){
        e.printStackTrace();
    } finally {
        // 4.关闭流
        if (null != os){
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != is){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 数据流

> 数据流保留了数据类型，处理八大基本数据类型和String字符串类型，读取顺序和写出顺序一致

##### DataInputStream & DataOutputStream

```java
public static void readAndWrite(){
    // 1.创建源
    // 2.选择流
    DataOutputStream os = null;
    DataInputStream is = null;
    try {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        os = new DataOutputStream(baos);
        // 3.操作流
        os.writeUTF("长城在");
        os.writeBoolean(false);
        os.writeChar(97);
        os.writeDouble(1.00);
        byte[] bytes = baos.toByteArray();

        // 读取的顺序和写入的顺序一致
        is = new DataInputStream(new ByteArrayInputStream(bytes));

        String s = is.readUTF();
        boolean b = is.readBoolean();
        char c = is.readChar();
        double v = is.readDouble();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        // 4.关闭流
        if (null != os){
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != is){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 对象流

> 只有实现 Serializable 接口才能实现序列化和反序列化

##### ObjectInputStream(序列化) & OjectOutputStream(反序列化)

```java
package top.simba1949.common;

import java.io.Serializable;

/**
 * @author SIMBA1949
 * @date 2019/5/20 22:41
 */
public class User implements Serializable {
    private static final long serialVersionUID = -3250898344958531492L;
    private Integer id;
    private String username;
    private Boolean flag;
    // getter/setter toString 方法省略
}
```

```java
public static void encodeAndDecode(){
    // 1.创建源
    User user = new User();
    user.setUsername("李白");
    user.setId(18);
    user.setFlag(true);
    // 2.选择流
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    try {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        oos = new ObjectOutputStream(baos);
        // 3.操作流
        oos.writeObject(user);
        byte[] bytes = baos.toByteArray();

        ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        User decodeUser = (User) ois.readObject();
        System.out.println(decodeUser);
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e){
        e.printStackTrace();
    } finally {
        if (null != oos){
            // 4.关闭流
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != ois){
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 打印流

##### PrintStream

```java
public static void printStream() throws FileNotFoundException {
    PrintStream ps = out;
    ps.println("打印流");
    ps.close();

    String str = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/printStream.txt";
    PrintStream printStream = new PrintStream(new BufferedOutputStream(new FileOutputStream(str)), true);
    // 打印流重定向到文件中
    System.setOut(printStream);
    printStream.println("重定向到文件中");
    printStream.close();

    // 在重定向到控制台
    System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)), true));
    System.out.println("重定向到控制台");

}
```

PrintWriter

```java
public static void printWriter() throws FileNotFoundException {
    String src = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/printWriter.txt";
    PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(new File(src))));
    printWriter.println("printWriter 流笔记");
    printWriter.flush();
    printWriter.close();
}
```

### 随机流

##### RandomAccessFile

```java
/**
	 * 体验 RandomAccessFile.seek(偏移量) 读取数据
	 * @throws IOException
	 */
public static void randomAccessFile() throws IOException {
    String src = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/fileio/randomAccessFileSrc.txt";
    RandomAccessFile rw = new RandomAccessFile(new File(src), "rw");
    rw.seek(2);
    byte[] flush = new byte[1024];
    int len = -1;
    while ((len = rw.read(flush)) != -1){
        System.out.println(new String(flush, 0, len));
        rw.write(flush, 0, len);
    }
    rw.close();
}

/**
	 * 拆分文件
	 * @throws IOException
	 */
public static void splitFileApp() throws IOException {
    String src = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/random/src/randomAccessFileSrc.txt";
    File file = new File(src);
    // 文件总字节数
    long length = file.length();
    // 每块区域的大小
    int blockSize = 200;
    // 多少块
    int totalNum = (int) (length % blockSize == 0 ? length/blockSize : length/blockSize + 1);

    for (int i = 0; i < totalNum; i++){
        if (i == (totalNum - 1)){
            // 说明是最后一块
            // 考虑最后一块区域
            int lastBlockSize = (int) (length % blockSize == 0 ? blockSize : (length - (length/blockSize) * blockSize));
            read(i, blockSize, lastBlockSize, file);
        }else {
            // 中间
            read(i, blockSize, blockSize, file);
        }
    }
}

public static void read(int blockNum, int blockSize, int readLength, File file) throws IOException {
    String dest = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/random/split/" + blockNum + ".tmp";
    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(dest)));
    RandomAccessFile ra = new RandomAccessFile(file, "rw");
    ra.seek(blockNum * blockSize);
    byte[] flush = new byte[readLength];
    ra.read(flush);
    bos.write(flush, 0, readLength);
    bos.close();
    ra.close();
}
```

### 合并流

##### SequenceInputStream

```java
public static void write() throws IOException {
    String splitDriPath = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/random/split";
    File file = new File(splitDriPath);

    // 创建输出源
    String destPath = "T:/IDE/IDEA/Workspace/java-learn/java-io/src/main/resources/file/random/group/group.txt";
    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destPath, true));

    Vector<InputStream> vector = new Vector<InputStream>();
    SequenceInputStream sis = null;
    for (int i = 0; i < file.listFiles().length; i++){
        vector.add(new BufferedInputStream(new FileInputStream(new File(splitDriPath + "/" + i + ".tmp"))));
    }
    sis = new SequenceInputStream(vector.elements());

    byte[] flush = new byte[1024];
    int len = -1;
    while ((len = sis.read(flush)) != -1){
        bos.write(flush, 0, len);
    }
    bos.flush();
    bos.close();
    sis.close();
}
```

## Common-IO

```java
public static void main(String[] args) {
    // 统计字节数
    long fileLen = FileUtils.sizeOf(new File(""));
    // 获取指定目录所有文件
    Collection<File> listFiles = FileUtils.listFiles(
        // 指定路径
        new File(""),
        // 文件过滤
        FileFilterUtils.and(new SuffixFileFilter("java")),
        // 文件夹过滤
        DirectoryFileFilter.INSTANCE);

    try {
        // 指定字符集读取文件
        String fileString = FileUtils.readFileToString(new File(""), "utf-8");
    } catch (IOException e) {
        e.printStackTrace();
    }

    try {
        // 读取每行数据
        List<String> strings = FileUtils.readLines(new File(""), "utf-8");
    } catch (IOException e) {
        e.printStackTrace();
    }

    try {
        // 将文件读取为字节数组
        byte[] bytes = FileUtils.readFileToByteArray(new File(""));
    } catch (IOException e) {
        e.printStackTrace();
    }

    try {
        //
        LineIterator lineIterator = FileUtils.lineIterator(new File(""));
        while (lineIterator.hasNext()){
            String next = lineIterator.next();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    try {
        // 将字符串写到文件中
        String data = "";
        FileUtils.write(new File(""), data,"utf-8", true);
    } catch (IOException e) {
        e.printStackTrace();
    }

    try {
        // 将字节数组写到文件中
        byte[] data = new byte[10];
        FileUtils.writeByteArrayToFile(new File(""), data);
    } catch (IOException e) {
        e.printStackTrace();
    }

    try {
        // 写列表
        List<String> list = new ArrayList<String>();
        FileUtils.writeLines(new File(""), list, true);
    } catch (IOException e) {
        e.printStackTrace();
    }

    try {
        // 拷贝文件
        FileUtils.copyFile(new File("srcFile"), new File("destFile"));
    } catch (IOException e) {
        e.printStackTrace();
    }

    try {
        // 拷贝文件夹
        FileUtils.copyDirectory(new File("srcDir"), new File("destDir"));
    } catch (IOException e) {
        e.printStackTrace();
    }

    try {
        // 拷贝文件到文件夹中
        FileUtils.copyFileToDirectory(new File("srcFile"), new File("destDir"));
    } catch (IOException e) {
        e.printStackTrace();
    }

    try {
        // 拷贝文件夹到文件夹下
        FileUtils.copyDirectoryToDirectory(new File("srcDir"), new File("destDir"));
    } catch (IOException e) {
        e.printStackTrace();
    }

    try {
        String datas = IOUtils.toString(new URL("https://www.baidu.com"), "UTF-8");
        System.out.println(datas);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```



