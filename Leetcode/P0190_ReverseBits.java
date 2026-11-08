package Leetcode;
// Code to reverse the bits of a number 
public class P0190_ReverseBits {
    public class Solution {
    public int reverseBits(int n) {
        int rev = 0;

        for (int i = 0; i < 32; i++) {
            rev = (rev << 1) | (n & 1);
            n = n >>> 1;
        }
        return rev;
    }
}
    
}
