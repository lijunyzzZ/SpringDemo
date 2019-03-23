package com.smart.aop;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface OperationLogger {
    /**
     * 模块名字
     */
    String modelName() default "";

    /**
     * 操作类型
     */
    String option();
}
