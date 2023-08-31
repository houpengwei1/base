package com.base.lx.arithmetic;

/**
 * 题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第四个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
 */
public class 兔子算法 {

    public static void main(String[] args) {
//        for (int i = 1; i <= 20; i++)
//            System.out.println(f(i));
            f2(5);
    }


    /**
     * 当传入的值为 n = 3 ， 即 f(3) ，此时返回 f(3-1) + f(3-2) ， 而 f(2) = 1 , f(1) = 1 ，即 f(3) = f(2) + f(1) = 2 。
     * 当传入的值为 n = 4 ， 即 f(4) ，此时返回 f(4-1) + f(4-2) <==> f(3) + f(2)， 由上述可知 f(3) = f(2) + f(1) = 2 , 而f(2) = 1 ，即 f(4) = f(2) + f(1) + f(2)，则 f(4) = 3 。
     */
    public static int f(int x) {
        if (x == 1 || x == 2)
            return 1;
        else
            return f(x - 1) + f(x - 2);
    }

    public static int f2(int x) {
        System.out.println(x);
        if (x == 1 || x == 2)
            return 1;
        else
            return f2(x - 1) + f2(x - 2);
    }

    // 只有return 的时候 才会跳出
    // 比如 x=3的时候   3-1 = 2 会满足return 但是还是走的 x=3 然后等于1的时候满足 return  才走x=2

}
