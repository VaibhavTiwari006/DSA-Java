package Leetcode;

public class P0771_JewelsAndStones {
    class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        int count=0;
        for(int i=0;i<stones.length(); i++){
            if(jewels.equals(stones)){
                count++;
            }
        }
        return count;
    }
}
}
