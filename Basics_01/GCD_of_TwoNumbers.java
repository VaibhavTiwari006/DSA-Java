package Basics_01;
import java.util.Scanner;
public class GCD_of_TwoNumbers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number 1 and 2 ");
        int num1= in.nextInt();
        int num2= in.nextInt();
        while(num2!=0)
        {
            int rem = num1 % num2;
            num1 = num2 ;
            num2 = rem;
        }
        System.out.println(num1);
        in.close();
    }
}
