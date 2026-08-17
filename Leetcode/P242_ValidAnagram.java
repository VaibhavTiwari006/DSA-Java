package Leetcode;
import java.util.Arrays;
public class P242_ValidAnagram {
    class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        String s1 = sortString(s);
        String t1 = sortString(t);
        if(!s1.equals(t1)){
            return false;
        }
        return true;
    }
    public String sortString(String str){
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
 }   
}