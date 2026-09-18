package Leetcode;

public class P1520_MaximumNumberofNonOverlappingSubstrings {
    class Solution {
        public java.util.List<String> maxNumOfSubstrings(String s) {
            int n = s.length();
            int[] first = new int[26];
            int[] last = new int[26];
            java.util.Arrays.fill(first, n);
            java.util.Arrays.fill(last, -1);
            for (int i = 0; i < n; i++) {
                int c = s.charAt(i) - 'a';
                first[c] = Math.min(first[c], i);
                last[c] = i;
            }
            java.util.List<String> ans = new java.util.ArrayList<>();
            int end = -1;
            for (int i = 0; i < n; i++) {
                int c = s.charAt(i) - 'a';
                if (first[c] != i)
                    continue;
                int right = last[c];
                boolean valid = true;
                for (int j = i; j <= right; j++) {
                    int x = s.charAt(j) - 'a';
                    if (first[x] < i) {
                        valid = false;
                        break;
                    }
                    right = Math.max(right, last[x]);
                }
                if (!valid)
                    continue;
                if (i > end)
                    ans.add("");
                ans.set(ans.size() - 1, s.substring(i, right + 1));
                end = right;
            }
            return ans;
        }
    }
}
