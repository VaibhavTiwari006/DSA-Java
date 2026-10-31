package Leetcode;

public class P3019_NumberOfChangingKeys {
    class Solution {
    public int countKeyChanges(String s) {
        int count =0;
        String ans =s.toLowerCase();
        for(int i =0 ; i< ans.length()-1; i++){
            char ch = ans.charAt(i);
            if( ch!=ans.charAt(i+1)){
                count++;
            }
        }
        return count;
    }
}
}
