package Basics_01;
import java.util.Scanner;
public class SimpleATM {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int balance = 1000000;   // initial balance
        System.out.println("Welcome to the ATM");

        System.out.println("Enter amount to withdraw:");
        int withdraw = sc.nextInt();

        if (withdraw <= balance) {
            balance = balance - withdraw;
            System.out.println("Withdrawal successful!");
            System.out.println("Remaining balance: Rs." +balance);
        } else {
            System.out.println("Insufficient balance!");
        }

        sc.close();
    }
}