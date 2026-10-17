package Leetcode;

public class P1160_FindWordsThatCanBeFormedByCharacters {
    class Solution {
        public int countCharacters(String[] words, String chars) {
            int ans = 0;
            for (String word : words) {
                StringBuilder temp = new StringBuilder(chars);
                boolean good = true;
                for (int i = 0; i < word.length(); i++) {
                    char ch = word.charAt(i);
                    int index = temp.indexOf(String.valueOf(ch));
                    if (index == -1) {
                        good = false;
                        break;
                    }
                    temp.deleteCharAt(index);
                }
                if (good) {
                    ans += word.length();
                }
            }
            return ans;
        }
    }
}