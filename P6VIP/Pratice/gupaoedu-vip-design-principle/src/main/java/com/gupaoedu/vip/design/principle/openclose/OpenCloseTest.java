package com.gupaoedu.vip.design.principle.openclose;

public class OpenCloseTest {
    public static void main(String[] args) {
        ICourse iCourse = new JavaDiscountCourse(1,"Java架构",11800D);
        JavaDiscountCourse discountCourse = (JavaDiscountCourse)iCourse;
        System.out.println("课程ID: " + discountCourse.getId() +
                     "\n课程标题: 《" + discountCourse.getName() + " 》" +
                     "\n售价: " + discountCourse.getPrice() +
                     "\n原价: " + discountCourse.getDiscountPrice()) ;
    }
}
