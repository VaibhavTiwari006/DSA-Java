package Leetcode;
public class P1929_ConcatenationOfArray {
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n+n];
        for(int i = 0 ; i < n ; i++){
            ans[i] = nums[i];
        }
        for(int i = 0 ; i < n ; i++ ){
            ans[n+i] = nums[i];
        }
        return ans;
    }
}
