package com.cn.zjj;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestNewInterface {

	public static void main(String[] args) {
		//Predicate接口，用于逻辑判断
		Predicate<Person> predicate=(s)->s.firstName.equals("zjj");
		System.out.println(predicate.test(new Person("zjj", "blue")));
		System.out.println(predicate.test(new Person("ljj", "red")));
		//加入复杂逻辑
		predicate=predicate.or((s)->s.lastName.equals("red")).and((s)->s.firstName.endsWith("jj"));
		System.out.println(predicate.test(new Person("zjj", "blue")));
		System.out.println(predicate.test(new Person("ljj", "red")));
		
		//Functiom接口
		Function<String, Integer> toInteger = Integer::valueOf;
		//A.andThen(B) 先执行A,在执行B，compose相反
		Function<String, Double> backToString = toInteger.andThen(Double::valueOf);
		System.out.println(backToString.apply("123"));
		
		//Supplier接口
		Supplier<Person> personSupplier = Person::new;
		Person person=personSupplier.get();   // new Person
		System.out.println(person);
		//Consumer接口
		Consumer<String> dosomething=(s)->System.out.println(s.substring(0,1));;
		Consumer<ArrayList<String>> iterate = (p) -> p.forEach(dosomething);
		ArrayList<String> al=new ArrayList<String>();
		al.add("资俊杰");
		al.add("张小杰");
		iterate.accept(al);
		//ComparatorComparator
		Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
		Person p1 = new Person("John", "Doe");
		Person p2 = new Person("Alice", "Wonderland");
		System.out.println(comparator.compare(p1, p2));             // > 0
		System.out.println(comparator.reversed().compare(p1, p2));  // < 0
		//Optional 接口
		/**
		*Optional 被定义为一个简单的容器，其值可能是null或者不是null。在Java 8之前一般某个函数应该返回非空对象但是偶尔却可能返回了null，而在Java 8中，不推荐你返回null而是返回Optional。
		*/
		Optional<String> optional = Optional.of("bam");
		optional.isPresent();           // true
		optional.get();                 // "bam"
		System.out.println(optional.orElse("fallback"));    // "bam"
		optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"
	}

}
