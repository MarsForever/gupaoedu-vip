package main.java.com.gupao.edu.reflection;

public class reLearnReflection {
    public static void main(String[] args) throws Exception {
        Class<User> class1 = User.class;
        Class<?> class2 = Class.forName("main.java.com.gupao.edu.reflection.User");
       // Class<? extends User> class3 = new User().getClass();
        Class<?> class4 = reLearnReflection.class.getClassLoader().loadClass("main.java.com.gupao.edu.reflection.User");

        System.out.println(class1.getModifiers());
        System.out.println(class1.getPackage());
        System.out.println(class1.getName());
        System.out.println(class1.getSuperclass());
        System.out.println(class1.getClassLoader());
        System.out.println(class1.getSimpleName());
        System.out.println(class1.getInterfaces().length);
        System.out.println(class1.getAnnotations().length);
    }
}
