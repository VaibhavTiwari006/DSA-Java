package Leetcode;

import java.util.HashSet;

public class P0575_DistributeCandies {
    class Solution {
    public int distributeCandies(int[] candyType) {
        HashSet <Integer> set = new HashSet<>();
        for (int candy : candyType){
            set.add(candy);
        }
        return Math.min(set.size(), candyType.length/2);
    }
}
}
