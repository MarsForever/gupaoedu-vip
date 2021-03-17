package main.java.com.gupao.edu.single;

public class PersonSingle {
    private static PersonSingle instance;
    private PersonSingle (){
        if(instance != null){
            throw new RuntimeException("Instance exits,can't create again");
        }
    }
    public static PersonSingle getInstance(){
        if(instance == null){
            instance = new PersonSingle();
        }
        return instance;
    }
}
