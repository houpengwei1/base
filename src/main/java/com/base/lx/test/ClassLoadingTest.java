package com.base.lx.test;
/**
 * 模拟jvm类加载过程
 */
public class ClassLoadingTest {
    int a = instanceMethod();
    static int b = staticMethod();

    static {
        System.out.println("static block1");
    }

    {
        System.out.println("instance block2");
    }

    public ClassLoadingTest(){
        System.out.println("constructor3");
    }

    public int instanceMethod(){
        System.out.println("instance method4");
        return 2;
    }

    public static int staticMethod(){
        System.out.println("static method5");
        return 2;
    }

    public static void main(String[] args) {
        ClassLoadingTest classLoadingTest = new ClassLoadingTest();
    }

}
