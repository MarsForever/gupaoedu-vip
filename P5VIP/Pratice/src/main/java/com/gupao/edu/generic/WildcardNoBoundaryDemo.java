package main.java.com.gupao.edu.generic;

import java.util.ArrayList;
import java.util.List;

public class WildcardNoBoundaryDemo {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("I");
        list1.add("am");
        list1.add("billionaire");
        loop(list1);
    }

    public static void loop(List<?> list){
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }
}
