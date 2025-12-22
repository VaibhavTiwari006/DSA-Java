package Basics_01;
import java.util.Scanner;
public class anybase_to_anybase {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number ");
        int n = in.nextInt();
        System.out.println("Enter the base of input number");
        int b1 = in.nextInt();
        System.out.println("Enter the base of output number");
        int b2 = in.nextInt();
        int dec = anybase_to_decimal(n, b1);
        int result = decimal_to_anybase(dec, b2);
        System.out.println(result);
        in.close();
    }
    // Step 1: any base to decimal
    public static int anybase_to_decimal(int n, int b1) {
        int dec = 0, p= 1;
        while (n > 0) {
            int dig = n % 10;
            n = n / 10;
            dec += dig * p;
            p = p * b1;
        }
        return dec;
    }
    // Step 2: decimal to any base
    public static int decimal_to_anybase(int dec, int b2) {
        int result = 0, p = 1;
        while (dec > 0) {
            int dig = dec % b2;
            dec = dec / b2;
            result += dig * p;
            p = p * 10;
        }
        return result;
    }
}
