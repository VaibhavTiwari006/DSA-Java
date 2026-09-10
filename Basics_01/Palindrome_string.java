package Basics_01;

public class Palindrome_string {

    public static void main(String[] args) {

        String rev = "";
        String str = "ABCDEDCBA";

        for (int i = str.length() - 1; i >= 0; i--) {
            char chr = str.charAt(i);
            rev = rev + chr;
        }

        if (str.equals(rev)) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not Palindrome");
        }
    }
}