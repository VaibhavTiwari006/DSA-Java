package Strings_03;
import java.util.Scanner;
public class BasicOfString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your name:");
        String name = sc.nextLine();

        System.out.println("Name: " + name);
        System.out.println("Length: " + name.length());
        System.out.println("Uppercase: " + name.toUpperCase());
        System.out.println("Lowercase: " + name.toLowerCase());
        System.out.println("First character: " + name.charAt(0));

        if (name.length() >= 3) {
            System.out.println("Substring (0 to 3): " + name.substring(0, 3));
        }

        String str2 = "Java";
        System.out.println("Equals 'Java'? " + name.equals(str2));
        System.out.println("Contains 'a'? " + name.contains("a"));

        sc.close();
    }
}