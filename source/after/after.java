package after;

interface Person {
    public void execute();
}

class ParentA implements Person {
    public void execute() {
        System.out.println("ParentAの処理");
    }
}

class ParentB implements Person {
    public void execute() {
        System.out.println("もっとイイ処理");
    }
}

class Child implements Person {
    private Person p; // 委譲(任される)されるヤツ

    public Child(Person p) {
        this.p = p;
    } // コンストラクタで確定する

    public void execute() {
        p.execute(); // 処理を フィールドのpに委譲する
    }
}

public class after {
    public static void main(String[] args) {
        Child child = null;
        Person p = null;

        p = new ParentA();

        child = new Child(p);
        child.execute();

        p = new ParentB();

        child = new Child(p);
        child.execute();

        Person[] pes = new Person[5];
        pes[0] = new ParentA();
        pes[1] = new ParentB();
        pes[2] = new ParentA();
        pes[3] = new ParentB();
        pes[4] = new ParentA();

        for (Person person : pes) {
            child = new Child(person);
            child.execute();
        }

    }
}