package Leetcode;

public class P2520_CountTheDigitsThatDivideANumber {
    class Solution {
        public int countDigits(int num) {
            int original = num;
            int count = 0;
            while (num > 0) {
                int dig = num % 10;
                if (original % dig == 0) {
                    count++;
                }
                num /= 10;
            }
            return count;
        }
    }
}
