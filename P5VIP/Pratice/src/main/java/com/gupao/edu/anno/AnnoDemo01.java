package main.java.com.gupao.edu.anno;

import java.util.Date;
@SuppressWarnings("all")
public class AnnoDemo01 {
    @Override
    public String toString() {
        return "AnnoDemo01{}";
    }
    @Deprecated
    public void show1(){
//        发现过时了，功能跟不上需求
    }

    @MyAnno(name = "MarsForever", age=8)
    public void show2(){
//        功能得到了提升，加强
    }
    public void demo(){
        show1(); //不推荐使用，但是可以使用
        show2();
        Date date = new Date();
        date.getYear();
    }
}
