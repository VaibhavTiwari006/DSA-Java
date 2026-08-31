package Leetcode;
public class P1323_Maximum69Number {
    class Solution {
    public int maximum69Number(int num) {
        StringBuilder str = new StringBuilder(String.valueOf(num));
        int index = str.indexOf("6");
        if (index != -1) {
            str.setCharAt(index, '9');
        }
        int result = Integer.parseInt(str.toString());
        return result;
    }
}
}
