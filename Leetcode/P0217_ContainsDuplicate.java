package Leetcode;
import java.util.HashSet;
public class P0217_ContainsDuplicate {
    class Solution {
        public boolean containsDuplicate(int[] nums) {
            HashSet<Integer> set = new HashSet<>();

            for (int num : nums) {
                if (set.contains(num)) {
                    return true;
                }
                set.add(num);
            }

            return false;
        }
    }
}
