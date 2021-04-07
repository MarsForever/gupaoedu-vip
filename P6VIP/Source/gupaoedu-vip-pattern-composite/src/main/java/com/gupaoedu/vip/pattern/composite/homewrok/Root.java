package com.gupaoedu.vip.pattern.composite.homewrok;

public abstract class Root {

    protected String name;

    public Root(String name) {
        this.name = name;
    }

    public abstract void show();
}