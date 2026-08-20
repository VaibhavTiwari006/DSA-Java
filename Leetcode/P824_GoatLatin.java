package Leetcode;
public class P824_GoatLatin {
    class Solution {
    public String toGoatLatin(String sentence) {

        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        String vowels = "aeiouAEIOU";

        for (int i = 0; i < words.length; i++) {

            String word = words[i];
            char first = word.charAt(0);

            // Check if first character is a vowel
            if (vowels.indexOf(first) != -1) {
                word = word + "ma";
            } else {
                // Move first character to the end
                word = word.substring(1) + first + "ma";
            }

            // Add 'a' (i + 1) times
            for (int j = 0; j <= i; j++) {
                word += "a";
            }

            // Add space between words
            if (i > 0) {
                result.append(" ");
            }

            result.append(word);
        }

        return result.toString();
    }
}
}