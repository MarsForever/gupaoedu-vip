package main.java.com.gupao.edu.reflection;

 public final class User {
     private String name;
     public String sex;
     public static String address;

     public User(){

     }
     public User(String name){
         this.name = name;
     }

     private User(String name, String sex) {
         this.name = name;
         this.sex = sex;
     }

     public String getName() {
         return name;
     }


     public static String getAddress() {
         return address;
     }

     public void jump(){
        System.out.println("jump...");
    }
    private void secret(){
        System.out.println("I have a secret");
    }

    public static void say(String msg){
        System.out.println("Say: " + msg);
    }
}
