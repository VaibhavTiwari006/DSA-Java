package Leetcode;

public class P1190_ReverseSubstringsBetweenEachPairOfParentheses {
    class Solution {
        public String reverseParentheses(String s) {
            int n = s.length();
            int[] opposite = new int[n];
            int[] stack = new int[n];
            int top = -1;

            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '(') {
                    stack[++top] = i;
                } else if (s.charAt(i) == ')') {
                    int openIndex = stack[top--];
                    opposite[openIndex] = i;
                    opposite[i] = openIndex;
                }
            }

            StringBuilder ans = new StringBuilder();
            int direction = 1;

            for (int i = 0; i < n; i += direction) {
                char ch = s.charAt(i);

                if (ch == '(' || ch == ')') {
                    i = opposite[i];
                    direction = -direction;
                } else {
                    ans.append(ch);
                }
            }

            return ans.toString();
        }
    }

}
