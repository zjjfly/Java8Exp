package com.cn.zjj;

public interface Java8Interface {
    double calculate(int a);

	default void say(){
		System.out.println("呵呵");
	}
}
