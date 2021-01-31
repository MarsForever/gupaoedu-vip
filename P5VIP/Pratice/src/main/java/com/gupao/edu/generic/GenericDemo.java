package main.java.com.gupao.edu.array;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class GenericDemo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> list = new ArrayList();
        list.add("1");
        list.add("hello");
        list.add("GUPAO");
        for(Object obj: list){
            System.out.println((String)obj);
        }

        Class<? extends List> aClass = list.getClass();
        Method method = aClass.getDeclaredMethod("add", Object.class);
        method.invoke(list,new Object());
        System.out.println(list);
    }
}
