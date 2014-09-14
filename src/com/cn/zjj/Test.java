package com.cn.zjj;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.crypto.Data;

public class Test {
	
	public static void main(String[] args) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date now =new Date(new Date().getTime()+185*3600000*24l);
	    System.out.println(sdf.format(now));
	}
}
