package before;

class Child extends ParentB {
}

class ParentA {
    public void execute() {
        System.out.println("ParentAの処理");
    }
}

class ParentB {
    public void execute() {
        System.out.println("もっとイイ処理");
    }
}

public class before {
    public static void main(String[] args) {
        Child child = new Child();
        child.execute();
    }
}