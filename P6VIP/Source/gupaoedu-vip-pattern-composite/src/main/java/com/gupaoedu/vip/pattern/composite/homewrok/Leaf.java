package com.gupaoedu.vip.pattern.composite.homewrok;

public class Leaf extends Root {

    public Leaf(String name) {
        super(name);
    }

    @Override
    public void show() {
        System.out.println(this.name);
    }
}
