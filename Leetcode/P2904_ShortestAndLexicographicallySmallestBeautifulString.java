package Leetcode;

public class P2904_ShortestAndLexicographicallySmallestBeautifulString {
    class Solution {
        public String shortestBeautifulSubstring(String s, int k) {
            String ans = "";
            for (int i = 0; i < s.length(); i++) {
                int count = 0;
                String subString = "";
                for (int j = i; j < s.length(); j++) {
                    subString += s.charAt(j);
                    if (s.charAt(j) == '1') {
                        count++;
                    }
                    if (count == k) {
                        if (ans.isEmpty()) {
                            ans = subString;
                        }
                        if (subString.length() < ans.length()) {
                            ans = subString;
                        }
                        if (subString.length() == ans.length()) {
                            if (subString.compareTo(ans) < 0) {
                                ans = subString;
                            }

                        }
                        break;
                    }

                }
            }
            return ans;

        }
    }

}
