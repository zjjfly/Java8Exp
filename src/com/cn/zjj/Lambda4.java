package com.cn.zjj;

public class Lambda4 {
	    static int outerStaticNum;
	    int outerNum;
	    void testScopes() {
	        Converter<Integer, String> stringConverter1 = (from) -> {
	            outerNum = 23;
	            return String.valueOf(outerNum+from);
	        };
	        Converter<Integer, String> stringConverter2 = (from) -> {
	            outerStaticNum = 72;
	            return String.valueOf(outerStaticNum+from);
	        };
	        System.out.println(stringConverter1.convert(17));
	        System.out.println(stringConverter2.convert(17));

	    }
	
}
