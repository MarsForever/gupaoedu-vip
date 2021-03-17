package main.java.com.gupao.edu.reflection;

public class ReflectionMeritAndDemerit {
    public static void main(String[] args) {
        int times = 10000000;
        String key = "BasketBall";
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            getInstanceByKey(key);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Total time:" + (endTime-startTime));
        long startTimeReflect = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            getInstanceReflectByKey(key);
        }
        long endTimeReflect = System.currentTimeMillis();
        System.out.println("Total time reflect: " + (endTimeReflect-startTimeReflect));


    }
    public static Ball getInstanceByKey(String key){
        if("BasketBall".equals(key)){
            return new BasketBall();
        }else if("FootBall".equals(key)){
            return new FootBall();
        }
        return null;
    }
    public static Ball getInstanceReflectByKey(String key) {
        String basePackage = "main.java.com.gupao.edu.reflection";
        Ball ball = null;
        try {
            Class clazz = Class.forName(basePackage + "." + key);
            ball = (Ball) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ball;
    }
}
