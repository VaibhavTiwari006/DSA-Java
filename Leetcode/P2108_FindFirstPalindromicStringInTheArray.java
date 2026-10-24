package Leetcode;

public class P2108_FindFirstPalindromicStringInTheArray {

    class Solution {
        public String firstPalindrome(String[] words) {
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                int left = 0;
                int right = word.length() - 1;

                while (left < right) {
                    if (word.charAt(left) != word.charAt(right)) {
                        break;
                    }
                    left++;
                    right--;
                }

                if (left >= right) {
                    return word;
                }
            }

            return "";
        }
    }

}
