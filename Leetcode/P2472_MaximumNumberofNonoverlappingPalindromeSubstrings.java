package Leetcode;
public class P2472_MaximumNumberofNonoverlappingPalindromeSubstrings {
    class Solution {
        public int maxPalindromes(String s, int k) {
            int count = 0;
            int i = 0;
            while (i + k <= s.length()) {
                if (isPalindrome(s, i, i + k - 1)) {
                    count++;
                    i += k;
                }
                else if (i + k < s.length()
                        && isPalindrome(s, i, i + k)) {
                    count++;
                    i += k + 1;
                }

                else {
                    i++;
                }
            }
            return count;
        }
        private boolean isPalindrome(String s, int left, int right) {
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
    }
}