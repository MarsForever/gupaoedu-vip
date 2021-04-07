package main.java.com.gupaoedu.vip.design.principle.dependencyinversion;

public class Tom {
// =============== v1 ============
//    public void studyJavaCourse(){
//        System.out.println("Tom 正在学习Java课程");
//    }
//    public void studyPythonCourse(){
//        System.out.println("Tom 正在学习Python课程");
//    }
//    public void studyAICourse(){
//        System.out.println("Tom 正在学习AI课程");
//    }

// =============== v2 ============
//    public void study(ICourse course){
//        course.study();
//    }
    private ICourse iCourse;
// =============== v3 ============
//    public Tom(ICourse iCourse) {
//        this.iCourse = iCourse;
//    }

    public void setiCourse(ICourse iCourse) {
        this.iCourse = iCourse;
    }

    public void study(){
        iCourse.study();
    }
}
