package Basics_01;

class Student {
    String name;
    int age;

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

public class ClassesObjects {
    public static void main(String[] args) {
        Student s1 = new Student();

        s1.name = "Vaibhav";
        s1.age = 19;

        s1.display();
    }
}