package com.base.lx.cs;

public interface TestController extends TestAbstract {

    public static void main(String[] args) {
        System.out.println( testAbstract.test(1,2));
    }

    TestAbstract testAbstract = new TestAbstract() {
        @Override
        public Integer test(Integer a, Integer b) {
            return a+b;
        }
    };


}
