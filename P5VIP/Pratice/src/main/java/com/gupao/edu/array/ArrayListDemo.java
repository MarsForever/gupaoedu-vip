package main.java.com.gupao.edu.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListDemo {

    private static List list = new ArrayList();
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.push(2);
        list.push(3);
        list.push(4);
        list.add(4);
        list.get(3);
        list.set(2,4);
//------------- 1.1.9 并发场景下的FailFast机制详解 ------------------
//    new ThreadIterator(list).start();
//    new ThreadAdd(list).start();
// ------------ 1.1.7 图解ArrayList源码------------------
//        ArrayList list = new ArrayList();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        System.out.println(list);
//
//        ArrayList list2 = new ArrayList(1);
    }

}
