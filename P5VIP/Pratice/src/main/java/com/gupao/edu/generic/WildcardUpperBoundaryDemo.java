package main.java.com.gupao.edu.generic;

import java.util.ArrayList;
import java.util.List;

public class WildcardUpperBoundaryDemo {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("I");
        list1.add("am");
        list1.add("billionaire");
//        loop(list1);
        List<Number> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        loop(list2);
    }

    /**
     *
     * @param list
     */
    public static void loop(List<? extends Number> list){
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }
}
