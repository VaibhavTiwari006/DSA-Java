package Leetcode;

import java.util.Arrays;

public class P1621_NumberofSetsofKNonOverlappingLineSegments {
    class Solution {
        public int numberOfSets(int n, int k) {
            final int MOD = 1_000_000_007;
            int[][] dp = new int[k + 1][n];
            Arrays.fill(dp[0], 1);
            for (int segments = 1; segments <= k; segments++) {
                long take = 0;
                for (int i = n - 2; i >= 0; i--) {
                    take = (take + dp[segments - 1][i + 1]) % MOD;
                    int skip = dp[segments][i + 1];
                    dp[segments][i] = (int) ((take + skip) % MOD);
                }
            }
            return dp[k][0];
        }
    }

}
