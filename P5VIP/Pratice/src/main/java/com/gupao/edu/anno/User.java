package main.java.com.gupao.edu.anno;


/**
 * 注解JavaDoc描述
 *
 * @author marsforever
 * @version 1.0
 * @since jdk1.5
 */
public class User {
    private Integer id;
    private String userName;



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }

    /**
     * 两数相加
     * @param a 第一个整数
     * @param b 第二个整数
     * @return 返回结果
     */
    @MyAnno(name="MarsForever")
    public int add(int a , int b){
        return 0;
    }

}
