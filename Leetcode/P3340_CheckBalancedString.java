package Leetcode;

public class P3340_CheckBalancedString {
    class Solution {
        public boolean isBalanced(String num) {
            int esum = 0, osum = 0;
            for (int i = 0; i < num.length(); i++) {
                int digit = num.charAt(i) - '0';
                if (i % 2 == 0) {
                    osum += digit;
                } else {
                    esum += digit;
                }
            }
            return esum == osum;
        }
    }
}
