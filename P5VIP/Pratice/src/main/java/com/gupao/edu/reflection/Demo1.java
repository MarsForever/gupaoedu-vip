package main.java.com.gupao.edu.reflection;


import java.lang.reflect.Method;

public class Demo1 {
    public static void main(String[] args) throws Exception {
        Class<User> Class = User.class;

        System.out.println(Class.getName());
        System.out.println(Class.getPackage());
        System.out.println(Class.getClassLoader());
        System.out.println(Class.getSuperclass());
        User user = Class.newInstance();
        Method method = Class.getDeclaredMethod("jump");
        method.invoke(user);

    }
}
