package Basics_01;

public class OOP2 {
    public static void main(String[] args) {

        Student s1 = new Student();
        s1.name = "Vaibhav";
        s1.age = 19;
        s1.roll = 303;
        s1.college = "CU";

        //Constructors --> To create an object

        System.out.println(s1.name);
        System.out.println(s1.age);
        System.out.println(s1.roll);
        System.out.println(s1.college);

        int x=5; //local variable (stored in stack memeory) --> no default variables
        System.out.println(x);
    }
}

class Student {
    String name; // information/data/characteristics --> instance variable
    int age;     // stored in heap --. Have default variables
    int roll;
    String college;

    void markAttendence() {  //behaviours --> functions --> instance method
        System.out.println("Attendece marked by " + name);
    }
    
}
