package main.java.com.gupao.edu.reflection;

public class BallMain {

    public static void main(String[] args) {
//        System.out.println(getInstanceByKey("basket"));
//        System.out.println(getInstanceByKey("football"));
        System.out.println(getInstanceReflectByKey("FootBall"));
    }
    public static Ball getInstanceByKey(String key){
        if("basket".equals(key)){
            return new BasketBall();
        }else if("football".equals(key)){
            return new FootBall();
        }
        return null;
    }
    public static Ball getInstanceReflectByKey(String key){
        String basePackage = "main.java.com.gupao.edu.reflection";
        Ball ball = null;
        try {
           Class clazz =  Class.forName(basePackage+"."+key);
           ball = (Ball) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ball;
    }
}
