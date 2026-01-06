package Strings_03;
import java.util.Scanner;
public class LongestWordInput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String sentence = sc.nextLine();
       
        String[] words = sentence.trim().split("\\s+");
        String longestWord = "";

        
        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }
        System.out.println("Longest Word: " + longestWord);
        System.out.println("Length: " + longestWord.length());
        sc.close();
    }
}