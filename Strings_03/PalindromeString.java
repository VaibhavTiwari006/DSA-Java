package Strings_03;
import java.util.*;
public class PalindromeString {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("enter the String");
        String word = in.nextLine();
        // Reverse the string
        String reversed = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            reversed += word.charAt(i);
        }
        System.out.println("Original: " + word);
        System.out.println("Reversed: " + reversed);

        // Check if palindrome
        if (word.equals(reversed)) {
            System.out.println(word + " is a palindrome");
        } else {
            System.out.println(word + " is not a palindrome");
        }
        in.close();
    }
}
