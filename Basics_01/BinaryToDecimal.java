package Basics_01;
import java.util.Scanner;
public class BinaryToDecimal {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a binary number: ");
        int binary = in.nextInt();
        int decimal = 0;
        int placeValue = 1;
        while (binary > 0) {
            int digit = binary % 10;
            if (digit != 0 && digit != 1) {
                System.out.println("Invalid binary number");
                in.close();
                return;
            }
            decimal = decimal + digit * placeValue;
            binary = binary / 10;
            placeValue = placeValue * 2;
        }
        System.out.println("Decimal: " + decimal);
        in.close();
    }
}