package main.java.com.gupao.edu.anno;
/**
 * 注释的本质就是接口
 */
public @interface MyAnno {
     String name();
     int age() default 18;  //指定默认值 在使用注解
//     String show1();
//     int show2();
//     String[] show3();
//     MyAnno2 show4();
//     PersonEnum show5();
}
