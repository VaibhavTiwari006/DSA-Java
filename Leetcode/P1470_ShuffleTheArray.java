package Leetcode;
public class P1470_ShuffleTheArray {
    class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] ans = new int[nums.length];
        for(int i=0; i<nums.length-n ; i++){
            ans[2*i] = nums[i];       
            ans[2*i+1] = nums[i+n]; 
        }
          return ans;
    }
 }   
}
