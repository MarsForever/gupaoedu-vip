package com.gupaoedu.vip.pattern.singleton.test.lazysimple;

import com.gupaoedu.vip.pattern.singleton.lazy.LazySimpleSingletion;

/**
 * Created by Tom.
 */
public class ExectorThread implements Runnable{

    public void run() {

        LazySimpleSingletion instance = LazySimpleSingletion.getInstance();
        System.out.println(Thread.currentThread().getName() + ":" + instance);

    }
}
