package com.cn.zjj;

public class FuncInterfaceTest {
	

	public static void main(String[] args) {
		//功能性接口
		Converter<Person,String > converter = Person::valueOf;
		//lambda写法：
//		Converter<Person,String > converter = (form)->Person.valueOf(form);
		String converted = converter.convert(new Person("李鹏","SB"));
		System.out.println(converted); 
		//传递方法给接口
		PersonFactory<Person> p=Person::new;
		Person s=p.create("zjj", "blue");
		System.out.println(s);
		//lambda表达式可以传入外层局部变量
		int num = 1;
		Converter<Integer, String> stringConverter =
		        (from) -> String.valueOf(from + num);
		System.out.println(stringConverter.convert(1));
		//lambda对于实例的字段与静态变量既可读又可写
		Lambda4 l= new Lambda4();
		l.testScopes();
	}
	
}
