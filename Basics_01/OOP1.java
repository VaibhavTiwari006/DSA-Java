package Basics_01;

public class OOP1 {
    public class Main {
        public static void main(String[] args) {
            Student s1 = new Student();
            Student s2 = new Student();

            s1.name = "Vaibhav";
            s1.age = 19;
            s1.roll = 303;
            s1.college = "CU";

            s2.name = "Mohit";
            s2.age = 19;
            s2.roll = 104;
            s2.college = "CU";

            s1.markAttendence();
            s2.markAttendence();
            s1.print();
            s2.print();

        }
    }
}

class Student {
    String name;
    int age;
    int roll;
    String college;

    void markAttendence() {
        System.out.println("Attendece marked by " + name);
    }

    void print() {
        System.out.println(name + " " + "," + roll + "," + "," + college);
    }
}
