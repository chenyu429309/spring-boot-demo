package com.felix.springbootdemo.aspects;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface TransactionalTest {
    String value() default "";

}

