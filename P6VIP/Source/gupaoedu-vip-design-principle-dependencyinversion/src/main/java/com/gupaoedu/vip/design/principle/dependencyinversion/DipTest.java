package main.java.com.gupaoedu.vip.design.principle.dependencyinversion;

public class DipTest {
    public static void main(String[] args) {
        // ================ v1 =================
//        Tom tom = new Tom();
//        tom.studyJavaCourse();
//        tom.studyPythonCourse();
//        tom.studyAICourse();
        // ================ v2 =================
//        Tom tom = new Tom();
//        tom.study(new JavaCourse());
//        tom.study(new PythonCourse());

        // ================ v3 =================
//        Tom tom = new Tom( new JavaCourse());
//        tom.study();
//
//        Tom tom2 = new Tom( new PythonCourse());
//        tom2.study();

        // ================ v4 =================
        Tom tom = new Tom();
        tom.setiCourse(new JavaCourse());
        tom.study();
        tom.setiCourse(new PythonCourse());
        tom.study();
    }
}
