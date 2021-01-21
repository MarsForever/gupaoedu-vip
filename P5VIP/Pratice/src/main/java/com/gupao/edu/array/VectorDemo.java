package main.java.com.gupao.edu.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VectorDemo {
    private static List list = new ArrayList();
    public static void main(String[] args) {
        /*Vector vector = new Vector();
        vector.add(1);*/
        List syncList =  Collections.synchronizedList(list);
    }
}
