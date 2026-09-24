package Basics_01;

public class P3550_SmallestIndexWithDigitSumEqualToIndex {
    class Solution {
        public int smallestIndex(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                int dsum = 0;
                while (nums[i] > 0) {
                    int dig = nums[i] % 10;
                    nums[i] = nums[i] / 10;
                    dsum += dig;
                }
                if (dsum == i) {
                    return i;
                }
            }
            return -1;

        }
    }

}
