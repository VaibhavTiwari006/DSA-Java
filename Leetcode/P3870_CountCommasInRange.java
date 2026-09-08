package Leetcode;
public class P3870_CountCommasInRange {
    class Solution {
    public int countCommas(int n) {
        if (n < 1000) {
            return 0;
        }
        return n - 999;
    }
}   
}
