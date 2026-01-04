package Strings_03;
import java.util.Scanner;
public class CapitalizeWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();
        String result = "";
        // Convert string to character array
        char[] chars = input.toCharArray();

        // Capitalize first character if it is a letter
        if (chars.length > 0 && chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] = (char)(chars[0] - 32);
        }

        // Loop through remaining characters
        for (int i = 1; i < chars.length; i++) {

            // If previous character is space, capitalize current character
            if (chars[i - 1] == ' ' && chars[i] >= 'a' && chars[i] <= 'z') {
                chars[i] = (char)(chars[i] - 32);
            }
        }

        // Convert character array back to string
        result = String.valueOf(chars);

        System.out.println("Capitalized Sentence: " + result);
        sc.close();
    }
}