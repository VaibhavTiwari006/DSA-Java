package Leetcode;

public class P1658_MinimumOperationsToReduceXtoZero {
    class Solution {
        public int minOperations(int[] nums, int x) {
            int total = 0;
            for (int num : nums) {
                total += num;
            }
            int target = total - x;
            if (target < 0)
                return -1;
            if (target == 0)
                return nums.length;

            int left = 0, sum = 0, longest = -1;
            for (int right = 0; right < nums.length; right++) {
                sum += nums[right];
                while (sum > target) {
                    sum -= nums[left];
                    left++;
                }
                if (sum == target) {
                    longest = Math.max(longest, right - left + 1);
                }
            }
            if (longest == -1) {
                return -1;
            }
            return nums.length - longest;
        }
    }
}