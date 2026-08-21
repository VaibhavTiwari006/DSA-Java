package Leetcode;
public class P500_KeyboardRow {
    class Solution {
        public String[] findWords(String[] words) {
            String row1 = "qwertyuiop";
            String row2 = "asdfghjkl";
            String row3 = "zxcvbnm";
            String[] result = new String[words.length];
            int count = 0;
            for (int i = 0; i < words.length; i++) {
                String word = words[i].toLowerCase();
                char first = word.charAt(0);
                String row;
                if (row1.indexOf(first) != -1) {
                    row = row1;
                }
                else if (row2.indexOf(first) != -1) {
                    row = row2;
                }
                else {
                    row = row3;
                }
                boolean valid = true;
                for (int j = 0; j < word.length(); j++) {
                    if (row.indexOf(word.charAt(j)) == -1) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    result[count] = words[i];
                    count++;
                }
            }

            String[] answer = new String[count];

            for (int i = 0; i < count; i++) {
                answer[i] = result[i];
            }
            return answer;
        }
    }
}