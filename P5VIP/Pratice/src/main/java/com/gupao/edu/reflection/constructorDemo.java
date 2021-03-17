package main.java.com.gupao.edu.reflection;

import java.lang.reflect.Constructor;

public class constructorDemo {
    public static void main(String[] args) throws Exception {
        Class<User> userClass = User.class;
        Constructor<?>[] constructors = userClass.getConstructors();
        for (Constructor c:constructors
             ) {
            System.out.println(c.getModifiers() + " " + c.getName());
        }
        System.out.println("==============================");
        Constructor<?>[] declaredConstructors = userClass.getDeclaredConstructors();
        for (Constructor c:declaredConstructors
             ) {
            System.out.println(c.getModifiers() + " " + c.getName());
        }
        System.out.println("==============================");
        User user = userClass.newInstance();
        Constructor<User> declaredConstructor = userClass.getDeclaredConstructor(String.class, String.class);
        declaredConstructor.setAccessible(true);
        System.out.println(declaredConstructor.newInstance("God","woman"));
    }
}
