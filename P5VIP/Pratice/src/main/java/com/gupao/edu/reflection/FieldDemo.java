package main.java.com.gupao.edu.reflection;

import java.lang.reflect.Field;

public class FieldDemo {
    public static void main(String[] args) throws Exception {
        Class<User> userClass = User.class;
        User user = userClass.newInstance();
        Field[] fields1 = userClass.getFields();
        for(Field f:fields1){
            System.out.println(f.getModifiers()+ " " +f.getName());
        }
        System.out.println("---------------------------------------");
        Field[] fields2 = userClass.getDeclaredFields();
        for (Field f:fields2) {
            System.out.println(f.getModifiers()+ " " + f.getName());
        }

        Field nameField = userClass.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(user,"God");
        System.out.println(user.getName());

        Field addressField = userClass.getDeclaredField("address");
        addressField.set(null,"Tokyo");
        System.out.println(User.getAddress());

    }
}
