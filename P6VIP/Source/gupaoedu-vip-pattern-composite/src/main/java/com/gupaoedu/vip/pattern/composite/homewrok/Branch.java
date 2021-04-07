package com.gupaoedu.vip.pattern.composite.homewrok;

import java.util.ArrayList;
import java.util.List;

public class Branch extends Root {
    private List<Root> roots;

    private Integer level;

    public Branch(String name, Integer level) {
        super(name);
        this.level = level;
        this.roots = new ArrayList<Root>();
    }

    @Override
    public void show() {
        System.out.println(this.name);
        for (Root dir : this.roots) {
            //控制显示格式
            if(this.level != null){
                for(int  i = 0; i < this.level; i ++){
                    //打印空格控制格式
                    System.out.print("  ");
                }
                for(int  i = 0; i < this.level; i ++){
                    //每一行开始打印一个+号
                    if(i == 0){ System.out.print("+"); }
                    System.out.print("-");
                }
            }
            //打印名称
            dir.show();
        }
    }

    public boolean add(Root root) {
        return this.roots.add(root);
    }

    public boolean remove(Root root) {
        return this.roots.remove(root);
    }

    public Root get(int index) {
        return this.roots.get(index);
    }

    public void list(){
        for (Root root : this.roots) {
            System.out.println(root.name);
        }
    }
}
