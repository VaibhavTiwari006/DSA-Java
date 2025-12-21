package Basics_01;
import java.util.Scanner;
public class Fibonacci_Series {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(" Fibonacci Series Program ");
        System.out.print("Enter the number of terms: ");
        int n = in.nextInt();
        System.out.println("Fibonacci series up to " + n + " terms:");
        printFibonacci(n); // call function
        in.close();
    }
    public static void printFibonacci(int n) {
        int first = 0;
        int second = 1;
        for (int i = 1; i <= n; i++) {
            System.out.print(first + " "); 
            int next = first + second;
            first = second;
            second = next;
        }
        System.out.println(); 
    }
}