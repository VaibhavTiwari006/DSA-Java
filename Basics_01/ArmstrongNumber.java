package Basics_01;

import java.util.Scanner;

public class ArmstrongNumber {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter the last value of the interval: ");
        int last = in.nextInt();

        if (last < 0) {
            System.out.println("Enter a non-negative number");
            in.close();
            return;
        }

        for (int i = 0; i <= last; i++) {

            int original = i;
            int temp = i;
            int count = 0;
            long ans = 0;

            // Count the number of digits
            if (temp == 0) {
                count = 1;
            } else {
                while (temp > 0) {
                    count++;
                    temp = temp / 10;
                }
            }

            // Restore temp
            temp = original;

            // Calculate the Armstrong sum
            while (temp > 0) {
                int digit = temp % 10;
                ans += (long) Math.pow(digit, count);
                temp = temp / 10;
            }

            // Print the number if it is an Armstrong number
            if (original == ans) {
                System.out.println(original);
            }
        }

        in.close();
    }
}