package Leetcode;
public class P125_ValidPalindrome {
    public boolean isPalindrome(String s) {
        String clean = "";
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if ((ch >= 'a' && ch <= 'z') ||
                (ch >= '0' && ch <= '9')) {

                clean += ch;
            }
        }
        String palin = "";
        for (int i = clean.length() - 1; i >= 0; i--) {
            palin += clean.charAt(i);
        }
        return clean.equals(palin);
    }
}