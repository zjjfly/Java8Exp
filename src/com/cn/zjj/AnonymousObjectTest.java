package com.cn.zjj;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjjfly on 16/1/27.
 */
public class AnonymousObjectTest{
    public static void main(String[] args) throws MalformedURLException {
        new Object(){
            void dos(){
                System.out.println("阿达");
            }

        }.dos();
        http://www.baidu.com
        System.out.println("");
        //使用初始化块快速创建对象
        Map map= new HashMap<Integer,String>(){
            {
                put(1,"hah");
            }
        };
        System.out.println(map.get(1));

    }


}
