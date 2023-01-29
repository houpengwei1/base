package com.base.lx.test;

/**
 * 手动装载/初始化模拟
 */
public class ClassLoadingInitTest {

    public static void main(String[] args) {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        try {
            //装载类
            System.out.println("装载类开始");
            //在使用ClassLoader默认的loadClass方法加载类时不会触发该类的初始化
            cl.loadClass("com.base.lx.test.ClassLoadingTest");
            System.out.println("装载类结束");

            //初始化类
            /**
             * 在使用 Class.forName 加载指定的类时,可以通过 initialize 参数设置是否需要对类进行初始化,默认true
             * 该方法有两种形式：Class.forName(String name, boolean initialize, ClassLoader loader)和 Class.forName(String className)。
             * 第一种形式的參数 name表示的是类的全名；initialize表示是否初始化类。loader表示载入时使用的类载入器(ClassLoader.getSystemClassLoader())。
             * 另外一种形式则相当于设置了參数 initialize的值为 true。loader的值为当前类的类载入器
             * 主要功能
             * Class.forName(xxx.xx.xx)返回的是一个类,作用是要求JVM查找并加载指定的类，也就是说JVM会执行该类的静态代码段。
             */
            System.out.println("初始化开始");
            //Class.forName("com.base.lx.test.ClassLoadingTest");
            Class.forName("com.base.lx.test.ClassLoadingTest",false,cl);
            System.out.println("初始化结束");
            ClassLoadingTest test = new ClassLoadingTest();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
