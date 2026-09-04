package Leetcode;

public class P3903_SmallestStableIndexI {
    class Solution {
        public int firstStableIndex(int[] nums, int k) {
            for (int i = 0; i < nums.length; i++) {
                int max = nums[0];
                int min = nums[i];

                for(int j=0; j<=1; j++){
                    max = Math.max(max,nums[j]);
                }

                for(int j=i; j <nums.length;j++){
                    min = Math.min(min,nums[j]);
                }

                if(max-min <= k){
                    return i;
                }
            }
            return -1;
        }
    }
}
