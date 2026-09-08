package Leetcode;

public class P0115_DistinctSubsequences {
    class Solution {
        public int numDistinct(String s, String t) {
            int m =s.length();
            int n =t.length();

            //Create a 2d dp array
            int[][] dp = new int[m+1][n+1];

            //Intialize dp[][] = 1 for all i
            for(int i=0; i<=m; i++){
                dp[i][0]=1;
            }

            //fill the dp array
            for(int i=1; i<=m; i++){
                for(int j=1; j<=n; j++){
                    if(s.charAt(i-1) == t.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                    }
                    else{
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }
            return dp[m][n];
        }
    }
}