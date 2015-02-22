package com.cn.zjj;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.junit.Before;
import org.junit.Test;

public class StreamTest {
	List<String> stringCollection = new ArrayList<>();
	Consumer<String> dosomething = System.out::println;

	@Before
	public void init() {
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");
	}

	// @Test
	public void TestFilter() {
		Predicate<String> prdt = (s) -> (s.charAt(0)) == 'a';
		stringCollection.stream().filter(prdt).forEach(dosomething);
		// "aaa2", "aaa1"
	}

	// @Test
	public void TestSort() {
		/**
		 * 如果你不指定一个自定义的Comparator则会使用默认排序。
		 * 排序只创建了一个排列好后的Stream，而不会影响原有的数据源，排序之后原数据stringCollection是不会被修改的
		 */
		Comparator<String> comparator = (p1, p2) -> p1.length() - p2.length();
		stringCollection.stream().sorted(comparator).forEach(dosomething);
		// "aaa1", "aaa2"
		System.out.println(stringCollection);
	}

	// @Test
	public void TestMap() {
		/**
		 * 中间操作map会将元素根据指定的Function接口来依次将元素转成另外的对象
		 */
		stringCollection.stream().map(String::toUpperCase)
				.sorted((a, b) -> b.compareTo(a)).forEach(dosomething);
		// "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1
	}

	// @Test
	public void TestMatch() {
		/**
		 * Stream提供了多种匹配操作，允许检测指定的Predicate是否匹配整个或部分Stream。所有的匹配操作都是最终操作，
		 * 并返回一个boolean类型的值。
		 */
		boolean anyStartsWithA = stringCollection.stream().anyMatch(
				(s) -> s.startsWith("a"));
		System.out.println(anyStartsWithA); // true
		boolean allStartsWithA = stringCollection.stream().allMatch(
				(s) -> s.startsWith("a"));
		System.out.println(allStartsWithA); // false
		boolean noneStartsWithZ = stringCollection.stream().noneMatch(
				(s) -> s.startsWith("z"));
		System.out.println(noneStartsWithZ); // true
	}

	// @Test
	public void TestCount() {
		long startsWithB = stringCollection.stream()
				.filter((s) -> s.length() == 4).count();
		System.out.println(startsWithB); // 3
	}

	@Test
	public void TestReduce() {
		OptionalInt reduced = stringCollection.stream().mapToInt((s)->s.length())
				.reduce((i1,i2)->i1+i2);
		reduced.ifPresent(System.out::println);
		//计算list中字符串的总长度
	}
}
