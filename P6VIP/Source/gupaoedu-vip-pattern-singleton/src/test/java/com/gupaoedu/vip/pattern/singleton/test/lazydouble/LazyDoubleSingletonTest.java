package com.gupaoedu.vip.pattern.singleton.test.lazydouble;

/**
 * Created by Tom.
 */
public class LazyDoubleSingletonTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(new ExectorThread());
        Thread t2 = new Thread(new ExectorThread());
        t1.start();
        t2.start();
        System.out.println("End");
    }
}
