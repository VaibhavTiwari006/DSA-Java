package Leetcode;

public class P3524_FindXValueofArrayI {
    class Solution {
        public long[] resultArray(int[] nums, int k) {
            long[] answer = new long[k];
            long[] previous = new long[k];
            for (int num : nums) {
                long[] current = new long[k];
                int remainder = num % k;
                current[remainder]++;
                for (int r = 0; r < k; r++) {
                    int newRemainder = (r * remainder) % k;
                    current[newRemainder] += previous[r];
                }
                for (int r = 0; r < k; r++) {
                    answer[r] += current[r];
                }
                previous = current;
            }
            return answer;
        }
    }

}
