package Basics_01;
import java.util.Scanner;
import java.util.Random;
public class GuessNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int secretNumber = random.nextInt(100) + 1; // 1 to 100
        int guess;
        int attempts = 0;
        System.out.println("Guess the number between 1 and 100");
        do {
            System.out.print("Enter your guess: ");
            guess = sc.nextInt();
            attempts++;

            if (guess > secretNumber) {
                System.out.println("Too high!");
            } else if (guess < secretNumber) {
                System.out.println("Too low!");
            } else {
                System.out.println("Correct! You guessed it in " + attempts + " attempts.");
            }
        } while (guess != secretNumber);
        sc.close();
    }
}
