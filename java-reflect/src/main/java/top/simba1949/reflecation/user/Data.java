package top.simba1949.reflecation.user;

import java.lang.annotation.*;

/**
 * @author SIMBA1949
 * @date 2019/5/31 9:57
 */
@Target(value = {ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Data {
}
