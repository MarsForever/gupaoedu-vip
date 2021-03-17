package main.java.com.gupao.edu.single;

import java.lang.reflect.Constructor;

public class SingleMain {
    public static void main(String[] args) throws Exception {
        PersonSingle p1 = PersonSingle.getInstance();
        PersonSingle p2 = PersonSingle.getInstance();
        PersonSingle p3 = PersonSingle.getInstance();
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        Constructor<? extends PersonSingle> declaredConstructor = p1.getClass().getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        System.out.println(declaredConstructor.newInstance());
    }
}
