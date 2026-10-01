package Basics_01;

class ParentAnimal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

class ChildDog extends ParentAnimal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

class ChildCat extends ParentAnimal {
    @Override
    void sound() {
        System.out.println("Cat meows");
    }
}

public class MethodOverriding {
    public static void main(String[] args) {
        ParentAnimal a;

        a = new ChildDog();
        a.sound();

        a = new ChildCat();
        a.sound();
    }
}