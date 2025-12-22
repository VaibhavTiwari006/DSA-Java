package Basics_01;
import java.util.Scanner;
public class anybase_addition {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        System.out.println("Enter first number ");
        int num1 = in.nextInt();
        System.out.println("Enter second number ");
        int num2 = in.nextInt();
        System.out.println("Enter the value of base");
        int b = in.nextInt();
        int sum = 0;
        int carry = 0;
        int p = 1;

        while (num1 > 0 || num2 > 0 || carry > 0) {

            int dig1 = num1 % 10;
            int dig2 = num2 % 10;
            num1 = num1 / 10;
            num2 = num2 / 10;
            int digSum = dig1 + dig2 + carry;
            int digit = digSum % b;
            carry = digSum / b;
            sum += digit * p;
            p = p * 10;
        }

        System.out.println("Sum = " + sum);
        in.close();
    }
}