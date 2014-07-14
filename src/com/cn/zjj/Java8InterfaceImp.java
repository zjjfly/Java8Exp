package com.cn.zjj;

public class Java8InterfaceImp {
	public static void main(String[] args) {

		Java8Interface j8i = new Java8Interface() {

			@Override
			public double calculate(int a) {
				return a*a;
			}
		};
		j8i.say();
		System.out.println(j8i.calculate(10));
	}
}
