package com.cn.zjj;

import java.lang.annotation.Repeatable;

@Repeatable(Hints.class) 
@interface Hint {
    String value();
}
