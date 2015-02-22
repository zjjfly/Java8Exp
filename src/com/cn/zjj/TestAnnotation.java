package com.cn.zjj;

public class TestAnnotation {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ClassNotFoundException {
		Class<HintUser> hintuser=(Class<HintUser>) Class.forName("com.cn.zjj.HintUser");
		Class<HintsUser> hintsuser=(Class<HintsUser>) Class.forName("com.cn.zjj.HintsUser");
		Class<Hint> hint=(Class<Hint>) Class.forName("com.cn.zjj.Hint");
		Class<Hints> hints=(Class<Hints>) Class.forName("com.cn.zjj.Hints");
		Hint hint1 = (Hint) hintsuser.getAnnotation(hint);
		System.out.println(hint1); // null
		Hints hints1 = (Hints) hintsuser.getAnnotation(hints);
		System.out.println(hints1.value().length); // 2
		Hint[] hints2 = (Hint[]) hintuser.getAnnotationsByType(hint);
		System.out.println(hints2.length); // 2
	}
}
