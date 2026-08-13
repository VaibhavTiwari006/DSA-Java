package Leetcode;

import java.util.HashSet;

public class P202_HappyNumber {

    public boolean isHappy(int n) {

        HashSet<Integer> set = new HashSet<>();

        while (n != 1 && !set.contains(n)) {
            set.add(n);

            int sq = 0;

            while (n > 0) {
                int dig = n % 10;
                sq += dig * dig;
                n = n / 10;
            }

            n = sq;
        }

        return n == 1;
    }
}