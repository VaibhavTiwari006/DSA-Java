package Basics_01;
import java.util.Scanner;
public class anybase_multiplication {
    // multiply num1 with one digit of num2
    static int multiplyWithDigit(int num1, int dig2, int b) {
        int carry = 0, ans = 0, p = 1;
        while (num1 > 0 || carry > 0) {
            int dig1 = num1 % 10;
            num1 /= 10;
            int prod = dig1 * dig2 + carry;
            int dig = prod % b;
            carry = prod / b;
            ans += dig * p;
            p *= 10;
        }
        return ans;
    }
    // any base addition
    static int anyBaseAddition(int n1, int n2, int b) {
        int carry = 0, ans = 0, p = 1;
        while (n1 > 0 || n2 > 0 || carry > 0) {
            int d1 = n1 % 10;
            int d2 = n2 % 10;
            n1 /= 10;
            n2 /= 10;
            int sum = d1 + d2 + carry;
            carry = sum / b;
            sum = sum % b;
            ans += sum * p;
            p *= 10;
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter first number");
        int num1 = in.nextInt();
        System.out.println("Enter second number");
        int num2 = in.nextInt();
        System.out.println("Enter the base");
        int b = in.nextInt();
        int ans = 0;
        int p = 1;
        while (num2 > 0) {
            int dig2 = num2 % 10;
            num2 /= 10;
            int partial = multiplyWithDigit(num1, dig2, b);
            ans = anyBaseAddition(ans, partial * p, b);
            p *= 10;
        }
        System.out.println("The answer is " + ans);
        in.close();
    }
}