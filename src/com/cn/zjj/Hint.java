package com.cn.zjj;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.TYPE)
@Repeatable(Hints.class) 
@interface Hint {
    String value();
}
