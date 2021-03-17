package main.java.com.gupao.edu.reflection;

import java.lang.reflect.Method;

public class refectAction {
    public static void                  main(String[] args) throws Exception {
        User user = new User();
        Class<User> userClass = User.class;
        Method[] methods = userClass.getMethods();
        for (Method m: methods) {
            System.out.println(m.getModifiers() + " " + m.getName());
        }
        System.out.println("===================================");
        Method[] declaredMethods = userClass.getDeclaredMethods();
        for(Method m:declaredMethods){
            System.out.println(m.getModifiers() + " " + m.getName());
        }
        System.out.println("===================================");
        Method secret = userClass.getDeclaredMethod("secret");
        secret.setAccessible(true);
        secret.invoke(user);
        Method sayMethod = userClass.getDeclaredMethod("say", String.class);
        sayMethod.invoke(null,"myGod");
    }
}
