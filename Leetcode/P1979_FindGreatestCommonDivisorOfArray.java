package Leetcode;

public class P1979_FindGreatestCommonDivisorOfArray {
    class Solution {
    public int findGCD(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        while(min!=0){
            int rem = max % min;
            max = min;
            min = rem;
        }
        return max;
    }
}
}
    
