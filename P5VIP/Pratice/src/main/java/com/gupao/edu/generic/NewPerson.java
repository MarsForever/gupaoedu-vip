package main.java.com.gupao.edu.generic;

public class NewPerson <T>{
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public NewPerson(T t) {
        this.t = t;
    }
}
