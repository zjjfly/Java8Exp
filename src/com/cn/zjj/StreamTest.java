package com.cn.zjj;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.junit.Assert;
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

	// @Test
	public void TestReduce() {
		OptionalInt reduced = stringCollection.stream()
				.mapToInt((s) -> s.length()).reduce((i1, i2) -> i1 + i2);
		reduced.ifPresent(System.out::println);
		// 计算list中字符串的总长度
	}

	//@Test
	public void TestParallelStream() {
		int max = 1000000;
		List<String> values = new ArrayList<>(max);
		for (int i = 0; i < max; i++) {
			UUID uuid = UUID.randomUUID();
			values.add(uuid.toString());
		}
		// 串行
		long t0 = System.nanoTime();
		long count = values.stream().sorted().count();
		System.out.println(count);
		long t1 = System.nanoTime();
		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out
				.println(String.format("sequential sort took: %d ms", millis));
		// 并行
		t0 = System.nanoTime();
		count = values.parallelStream().sorted().count();
		System.out.println(count);
		t1 = System.nanoTime();
		millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("parallel sort took: %d ms", millis));
	}

	@Test
	public void TestNewMapFeature() {
		Map<Integer, String> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
		   map.putIfAbsent(i, "val" + i); 
		}
		//新的遍历方法
		map.forEach((id, val) -> System.out.println(val));
		//如果map中已有特定的key，对这个键值对做一些操作
		map.computeIfPresent(3, (num, val) -> val + num);
		map.get(3);             // val33
		map.computeIfPresent(9, (num, val) -> null);
		map.containsKey(9);     // false
		//如果map中没有特定的key，做一些操作
		map.computeIfAbsent(23, num -> "val" + num);
		map.containsKey(23);    // true
		System.out.println(map.get(23));
		map.computeIfAbsent(3, num -> null);
		map.get(3);             // val33
		//删除特定的键值对
		map.remove(3, "val3");
		map.get(3);             // val33
		map.remove(3, "val33");
		map.get(3);             // null
		//有特定的key则返回对应的value，否则返回特定的值(和map的value相同类型的)
		System.out.println(map.getOrDefault(42, "not found"));  // not found
		//对map的元素进行合并
		map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
		System.out.println(map.get(9));             // val9
		map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
		System.out.println(map.get(9));             // val9concat
	}
}
