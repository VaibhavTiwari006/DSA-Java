package Leetcode;

public class P1096_BraceExpansionII {
    class Solution {
    private String expression;
    private int index;

    public java.util.List<String> braceExpansionII(String expression) {
        this.expression = expression;
        index = 0;
        return new java.util.ArrayList<>(parseExpression());
    }

    private java.util.Set<String> parseExpression() {
        java.util.Set<String> result = parseTerm();

        while (index < expression.length() && expression.charAt(index) == ',') {
            index++;
            result.addAll(parseTerm());
        }

        return result;
    }

    private java.util.Set<String> parseTerm() {
        java.util.Set<String> result = new java.util.TreeSet<>();
        result.add("");

        while (index < expression.length()
                && expression.charAt(index) != ','
                && expression.charAt(index) != '}') {
            java.util.Set<String> part = parseFactor();
            java.util.Set<String> combined = new java.util.TreeSet<>();

            for (String left : result) {
                for (String right : part) {
                    combined.add(left + right);
                }
            }
            result = combined;
        }

        return result;
    }

    private java.util.Set<String> parseFactor() {
        if (expression.charAt(index) == '{') {
            index++;
            java.util.Set<String> result = parseExpression();
            index++;
            return result;
        }

        StringBuilder word = new StringBuilder();
        while (index < expression.length()
                && Character.isLetter(expression.charAt(index))) {
            word.append(expression.charAt(index++));
        }

        java.util.Set<String> result = new java.util.TreeSet<>();
        result.add(word.toString());
        return result;
    }
}
    
}
