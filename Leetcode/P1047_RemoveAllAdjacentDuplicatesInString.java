package Leetcode;
import java.util.Stack;
public class P1047_RemoveAllAdjacentDuplicatesInString {
    public String removeDuplicates(String s) {
        if (s.length() == 0) {
            return "";
        }
        Stack<Character> st = new Stack<>();
        st.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (st.peek() == ch) {
                st.pop();
            } else {
                st.push(ch);
            }
        }
        StringBuilder result = new StringBuilder();
        for (char ch : st) {
            result.append(ch);
        }
        return result.toString();
    }
}