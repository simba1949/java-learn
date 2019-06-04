# Java执行 JavaScript 代码

Rhino

## Java 脚本引擎

- 使得 Java 应用程序可以通过一套固定的接口与各种脚本引擎交互，从而达到在 Java 平台上调用脚本语言的目的
- Java 脚本 API 是连通 Java 平台和脚本语言的桥梁
- 可以把一些复杂异变的业务逻辑交给脚本语言处理，提高开发效率

## 获取脚本引擎对象

```java
package top.simba1949.script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author SIMBA1949
 * @date 2019/6/1 15:50
 */
public class ScriptEngineLearn {
    public static void main(String[] args) throws ScriptException, NoSuchMethodException, IOException {
        // 获取脚本引擎对象
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine engine = scriptEngineManager.getEngineByName("javascript");

        // 定义变量，存储在引擎上下文中
        engine.put("msg", "君不见黄河之水天上来");
        String str = "var user = {'name':'李白', 'age':18};";
        str += "print(user.name);";
        // 执行 JS 代码
        engine.eval(str);
        engine.eval("msg = '奔流到海不复回'");
        // Java 获取变量值
        System.out.println(engine.get("msg"));

        // 定义 Js 函数
        engine.eval("function add(a,b) { var sum = a + b; return sum;}");
        // 取得调用 JS 函数的接口
        Invocable invocable = (Invocable) engine;
        // 执行 JS 函数，获取返回结果
        Object result = invocable.invokeFunction("add", 2, 3);

        // 执行 JS 文件
        // 获取classes目录
        String path = ScriptEngineLearn.class.getClassLoader().getResource("").getPath();
        FileReader reader = new FileReader(path + "js/js.js");
        // 执行js代码
        engine.eval(reader);
        reader.close();
    }
}
```





## 