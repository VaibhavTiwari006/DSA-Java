package Leetcode;

public class P3871_CountCommasInRangeII {
    class Solution {
        public long countCommas(long n) {
            long count = 0;
            for (long start = 1000; start <= n; start *= 1000) {
                count += n - start + 1;
            }
            return count;
        }
    }

}
