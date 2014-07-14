package com.cn.zjj;

public class FuncInterface {
	@FunctionalInterface
	interface Converter<F, T> {
		T convert(F from);
	}

	public static void main(String[] args) {
		Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
		Integer converted = converter.convert("123");
		System.out.println(converted); // 123
	}
}
