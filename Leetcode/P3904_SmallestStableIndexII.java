package Leetcode;
public class P3904_SmallestStableIndexII {
    class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int[] suffixMin = new int[nums.length];
        suffixMin[nums.length-1] = nums[nums.length-1];

        for(int i=nums.length-2; i>=0; i--){
            suffixMin[i] = Math.min(nums[i], suffixMin[i+1]);
        }
        int max=nums[0];
        for(int i=0; i<nums.length; i++){
            max= Math.max(max,nums[i]);
            if(max-suffixMin[i] <=k){
                return i;
            }
        }
        return -1;
    }
}   
}
