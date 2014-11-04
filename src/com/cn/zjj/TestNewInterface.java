package com.cn.zjj;

import java.util.function.Function;
import java.util.function.Predicate;

public class TestNewInterface {

	public static void main(String[] args) {
		//Predicate接口，用于逻辑判断
		Predicate<Person> p=(s)->s.firstName.equals("zjj");
		System.out.println(p.test(new Person("zjj", "blue")));
		System.out.println(p.test(new Person("ljj", "red")));
		//加入复杂逻辑
		p=p.or((s)->s.lastName.equals("red")).and((s)->s.firstName.endsWith("jj"));
		System.out.println(p.test(new Person("zjj", "blue")));
		System.out.println(p.test(new Person("ljj", "red")));
		
		//
		Function<String, Integer> toInteger = Integer::valueOf;
		//A.andThen(B) 先执行A,在执行B，compose相反
		Function<String, Double> backToString = toInteger.andThen(Double::valueOf);
		System.out.println(backToString.apply("123"));
		
	}

}
