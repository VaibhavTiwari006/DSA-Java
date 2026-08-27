package Leetcode;
public class P389_FindTheDifference {
    class Solution {
        public char findTheDifference(String s, String t) {
            StringBuilder str = new StringBuilder(s);
            for (int i = 0; i < t.length(); i++) {
                char ch = t.charAt(i);
                int index = str.indexOf(String.valueOf(ch));
                if (index == -1) {
                    return ch;
                }
                str.deleteCharAt(index);
            }
            return ' ';
        }
    }
}
