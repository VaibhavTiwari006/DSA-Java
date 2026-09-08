package Leetcode;

import java.util.HashMap;

public class P0940_DistinctSubsequencesII {
    class Solution {
    public int distinctSubseqII(String s) {
        long MOD = 1_000_000_007;
        long dp[] = new long[s.length()+1];
        dp[0]=1;
        HashMap<Character , Integer> lo = new HashMap<>();
        for(int i=1; i< dp.length; i++){
            char ch = s.charAt(i-1);
            dp[i] = (2 * dp[i - 1]) % MOD;
            if(lo.containsKey(ch)){
                int j = lo.get(ch);
                dp[i] = (dp[i] - dp[j - 1] + MOD) % MOD;
            }
            lo.put(ch,i);
        }
        return (int) ((dp[s.length()] - 1 + MOD) % MOD);
    }
}
}