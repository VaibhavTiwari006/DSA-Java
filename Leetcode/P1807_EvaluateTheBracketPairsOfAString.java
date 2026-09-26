package Leetcode;

public class P1807_EvaluateTheBracketPairsOfAString {
    class Solution {
        public String evaluate(String s, java.util.List<java.util.List<String>> knowledge) {
            java.util.Map<String, String> map = new java.util.HashMap<>();

            for (java.util.List<String> pair : knowledge) {
                map.put(pair.get(0), pair.get(1));
            }

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    int end = s.indexOf(')', i);
                    String key = s.substring(i + 1, end);
                    result.append(map.getOrDefault(key, "?"));
                    i = end;
                } else {
                    result.append(s.charAt(i));
                }
            }

            return result.toString();
        }
    }
}
