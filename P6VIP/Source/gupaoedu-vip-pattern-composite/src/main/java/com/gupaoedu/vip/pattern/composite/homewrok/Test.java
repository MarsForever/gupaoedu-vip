package com.gupaoedu.vip.pattern.composite.homewrok;

class Test {
    public static void main(String[] args) {

        // 根节点Root
        Branch root = new Branch("Root",1);

        // 分支1
        Branch branch1 = new Branch("Branch1",2);
        Leaf leaf1 = new Leaf("Leaf1");
        Leaf leaf2 = new Leaf("Leaf2");
        branch1.add(leaf1);
        branch1.add(leaf2);
        root.add(branch1);

        // 叶子3
        Leaf leaf3 = new Leaf("leaf3");
        root.add(leaf3);

        // 分支2
        Branch branch2 = new Branch("Branch2",2);
        Leaf leaf4 = new Leaf("Leaf4");
        Leaf leaf5 = new Leaf("Leaf5");
        branch2.add(leaf4);
        branch2.add(leaf5);
        // 分支2中加上分支3
        Branch branch3 = new Branch("Branch3",3);
        branch3.add(new Leaf("Leaf6"));
        branch2.add(branch3);
        root.add(branch2);

        // 效果展示
        root.show();
    }
}
