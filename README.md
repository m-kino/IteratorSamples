
今日の目標。

左辺が List 右辺がArrayList の違和感をなくす
    List<String> list = new ArrayList<String>();
の件です。


>抽象クラスとインターフェースはどういった違いがあるのでしょうか。
>一部実装にできるとか単一継承であるといったことよりは、どういった使い分けなのか気になります


全部のメソッドが abstract な抽象クラスと、Interfaceはおなじ？
と思うかもしれませんが、明確に違います。
また中身がないコードになんか意味ある？とか。


親クラスを作って「機能を継承」するために、抽象クラスを使いますよね。
おもに、コードの再利用が目的です。
で、interfaceはまさに「インタフェース」で、形をそろえることで、
プログラム同士の結合を弱くするために使います。ここでの結合を弱く、
の意味は「機能を切り替えたいときに、ソースの修正(再コンパイル)が
不要である」ということです。


たとえば


    public class ParentA {
      public void execute(){
        なんか処理
      }
    }
    
    public class Child extends ParentA {
    }
    
    なんてコードがあったときに、
    
    public static void main(String[] args){
      Child child = new Child();
      child.execute();
    }

こんなふうに、Childは親のexecuteをつかえますよね。
で、あとから、ParentBが出てきてそっちを使う必要がでたときに、
上記だとChildクラスは再コンパイルになると思います。
(こんなことはよくあって、もっとイイ機能を作ったから、
切り替えるみたいな)


それを再コンパイルなしに、ChildがParentBのメソッドを呼び出せる
ようにする機能がinterfaceです。


どうすればよいか考えてみてください。
この考え方、よいプログラムを書くために「絶対に」マスターしてほしい
概念なので、もうすこしツッコんだ議論をちゃんとやりたいと思います。

ググるために用語のヒントをだすと、
・継承より委譲を使いましょう
・デザインパターン
などです

--------------------------------------------------------------------------------


答えを書きます。あらためて目的は

>> それを再コンパイルなしに、ChildがParentBのメソッドを呼び出せる
>> ようにする機能がinterfaceです。


これです。
初めのコードはこうでした。

    class Child extends ParentA {
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
    
    public class Main {
        public static void main(String[] args) {
            Child child = new Child();
            child.execute();
        }
    }


さて、コードを変形していく過程を書きます



Childも、ParentA も、新たなParentBも、どれもexecuteというメソッドがあるので、それをおなじ「インタフェース」だと考え、interfaceを作ります。

    interface Person {
        public void execute();
    }

それらのクラスは、そのinterfaceを実装(implements)します


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

さてChildは、ParentAやBの機能を使うわけですが、継承だと
class Child extends ParentA (とか ParentB) と固定的になるため
おなじく class Child implements Person {... とします。

さて、ココからが委譲です。具体的にChildは、以下のようにします。

    class Child implements Person {
        private Person p;   // 委譲(任される)されるヤツ
        public Child(Person p) {this.p = p;}  // コンストラクタで確定する
        public void execute() {
            p.execute();   // 処理を フィールドのpに委譲する
        }
    }

フィールドに Person のインスタンスをもち、execute の実装は、
そいつに処理をお願いするようにします。
(処理をお願い = 委譲)。
これで、executeの処理は、コンストラクタから渡される変数が
ParentA型 かParentB型 かによって、動的に切り替えることが
できるようになりました。

つまり、具体的には、

    public class Main {
        public static void main(String[] args) {
            Child child = null;
            Person p = null;
    
            p = new ParentA();
            child = new Child(p);
            child.execute();
    
            p = new ParentB();
            child = new Child(p);
            child.execute();
        }
    }
    
こういうことです。1回目のexecuteは、ParentAのexecuteを実行します。
2回目のexecuteは、ParentBのexecuteを実行します。
つまり、コンパイルすることなしに、おなじChildクラスの処理を切り替える
ことができるようになりました。


継承はソースコード上、決定的に固定的なつながりをもってしまうけど、
委譲を使うことで、コード上のつながりが疎になる、というのが分かればOKです


またinterfaceというのは「中身はよく分かんないけどexecuteってメソッドをもっている」というまさにインタフェースを規定するものです。

Childなどは、中身はまさに使う瞬間(newされるとき) まで決まりません
が、とにかくexecuteというメソッドはもっているコトを規定しています。


難しかったと思いますが、ちょっとinterfaceのイメージや、
「継承より委譲がイイ」というのがつかめましたでしょうか。


ちょっと熟考してみてください。 











