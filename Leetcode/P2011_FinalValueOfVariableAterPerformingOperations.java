package Leetcode;
public class P2011_FinalValueOfVariableAterPerformingOperations {
    class Solution {
    public int finalValueAfterOperations(String[] operations) {
        int ans = 0;
        for (String op : operations) {
            if (op.charAt(1) == '+') {
                ans++;
            } else {
                ans--;
            }
        }
        return ans;
    }
}   
}
