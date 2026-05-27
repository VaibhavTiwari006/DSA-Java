package Basics_01;

public class Basic_OOPs {
    public static class Person{
        int age;
        String name;

        void saysHi(){
            System.out.println(name + "[" + age + "] says hi");
        }
    }

    public static void main(String[] args){
        Person p1 = new Person();
        p1.age = 10;
        p1.name = "A";
        p1.saysHi();

        Person p2 = new Person();
        p2.age = 20;
        p2.name = "B";
        p2.saysHi();

    }
    
}
