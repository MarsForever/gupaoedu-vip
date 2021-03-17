package main.java.com.gupao.edu.generic;


public class ClassDemo <K, V>{
    /**
     *
     * @param k
     * @param v
     * @return
     */
    public K method(K k,V v){
        return (K)null;
    }

    public <T> T method2(T t, V v){
        return (T)null;
    }

    public static <K> K method3(){
        return null;
    }
}
