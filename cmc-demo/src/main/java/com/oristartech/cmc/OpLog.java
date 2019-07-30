package com.oristartech.cmc;

import java.lang.annotation.*;

/**
 * @ClassName: OpLog
 * @Description 操作日志注解
 * @Author LKG
 * @date 2019/3/21
 * @Version 1.0
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OpLog {

    /*
     * 操作名称
     */
    String opName();

    /*
     * 操作描述
     */
    String opDesc() default "";
}
