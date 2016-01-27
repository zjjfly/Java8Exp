package com.cn.zjj;

/**
 * Created by zjjfly on 16/1/27.
 */
public class ClassLoadTest {
    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoadTest.class.getClassLoader();
        System.out.println(classLoader);
        //=sun.misc.Launcher$AppClassLoader@29453f44
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        //=sun.misc.Launcher$AppClassLoader@29453f44
        //说明系统类加载器就是AppClassLoader,应用程序类加载器,因为hash值相同,所以是同一个类的同一对象
        //它从环境变量classpath或者系统属性java.class.path所指定的目录中加载类，它是用户自定义的类加载器的默认父加载器。
        ClassLoader extClassLoader = classLoader.getParent();
        System.out.println(extClassLoader);
        //=sun.misc.Launcher$ExtClassLoader@266474c2
        //AppClassLoader的父加载器是ExtClassLoader
        //它从java.ext.dirs系统属性所指定的目录中加载类库，或者从JDK的安装目录的jre\lib\ext子目录(扩展目录)下加载类库
        System.out.println(extClassLoader.getParent());
        //=null
        //而ExtClassLoader的父加载器返回结果为null，实际上它的父加载器是BootStrap
        //这个加载器是用c++写的,用来加载系统属性sun.boot.class.path所指定的目录

        //java类加载器使用委托机制,一个类的加载总会先委托给父加载器,父加载器再委托给它的父加载器,一直到bootstrap加载器,一个类加载器有且只有一个父类加载器.

        new Thread(new Runnable() {

            @Override
            public void run() {
                ClassLoader threadcontextClassLosder = Thread.currentThread().getContextClassLoader();
                System.out.println(threadcontextClassLosder); //sun.misc.Launcher$AppClassLoader@19821f
            }
        }).start();
        //可以看到子线程的类加载器和主线程中的AppClassLoader是同一个对象
        //Thread对象的setContextClassLoader方法可以为线程设置类加载器

        //下面验证类加载器的可见性，也就是 子类的加载器可以看见所有的父类加载器加载的类，而父类加载器看不到子类加载器加载的类。
        //以下代码使用父加载器ExtClassLoader加载子加载器AppClassLoader路径下的类，由输出可知，是不可能实现的。
        //所以父加载器不能加载应该被子加载器加载的类。也就是说这个类在父加载器中不可见。这种机制依赖于委派机制
        try {
            Class.forName("com.cn.zjj.ClassLoadTest", true,
                    ClassLoadTest.class.getClassLoader().getParent());
            System.out.println("1 -- 类被加载");
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            System.out.println("1 -- 未找到类");
        }

        try {
            Class.forName("java.lang.String", true,
                    ClassLoadTest.class.getClassLoader());
            System.out.println("2 -- 类被加载");
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            System.out.println("2 -- 未找到类");
        }
        //父加载器加载的类对于子加载器来说是可见的。这同样依赖于委派机制。
        //其实在虚拟机启动初期，java.lang.String已经被BootStrap预加载了，这时再次加载，虚拟机发现已经加载，不会再重复加载。这同时也证明了类加载器的单一性。
    }
}
