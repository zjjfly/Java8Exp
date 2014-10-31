package com.cn.zjj;

@FunctionalInterface
interface Converter<F, T> {
	T convert(F from);
}