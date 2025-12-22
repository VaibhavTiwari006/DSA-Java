package Basics_01;
import java.util.Scanner;
public class Pythagorean_Triplet {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter three number");
        int a=in.nextInt();
        int b=in.nextInt();
        int c=in.nextInt();
        if (isPythagoreanTriplet(a, b, c)) {
            System.out.println("Yes, it is a Pythagorean Triplet.");
        } else {
            System.out.println("No, it is NOT a Pythagorean Triplet.");
        }
        in.close();
    }
    public static boolean isPythagoreanTriplet(int a, int b, int c) {
        return (a * a + b * b == c * c)
            || (a * a + c * c == b * b)
            || (b * b + c * c == a * a);
    }
}

