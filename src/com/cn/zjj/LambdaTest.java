package com.cn.zjj;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 旧版的list排序
		List<Integer> names = Arrays.asList(155, 53, 74, 34);
		Collections.sort(names, new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return a > b ? 1 : -1;
			}
		});
		System.out.println("旧版，从小到大排序：");
		for (Integer s : names) {
			System.out.println(s);
		}
		// 用lambda实现排序
		// Collections.sort(names,(Integer a,Integer b)->{
		// return b.compareTo(a);
		// });
		// 最简形式
		Collections.sort(names, (a, b) -> b.compareTo(a));
		System.out.println();
		System.out.println("新版，从大到小排序：");
		for (Integer i : names) {
			System.out.println(i);
		}
	}
}
