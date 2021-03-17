package main.java.com.gupao.edu.generic;

public class MethodForPersonDemo {
    public static void main(String[] args) {
        Person p = new Person();
        p.setIdCard("I am a billionaire");

        PersonBean bean = new PersonBean();
        bean.setAddress("Tokyo");
        bean.setName("Mars");
        bean.setAge(18);

        NewPerson<PersonBean> np = new NewPerson(bean);
        NewPerson<String> np1 = new NewPerson<>("hello");
    }

}
