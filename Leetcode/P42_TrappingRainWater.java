package Leetcode;
public class P42_TrappingRainWater {
    class Solution {
        public int trap(int[] height) {
            int Lmax = 0;
            int Rmax = 0;
            int total = 0;
            int l = 0;
            int r = height.length - 1;

            while (l < r) {
                if (height[l] <= height[r]) {

                    if (height[l] < Lmax) {
                        total += Lmax - height[l];
                    } else {
                        Lmax = height[l];
                    }
                    l++;

                } else {
                    if (height[r] < Rmax) {
                        total += Rmax - height[r];
                    } else {
                        Rmax = height[r];
                    }
                    r--;
                }
            }

            return total;
        }
    }
}