package Leetcode;
import java.util.HashSet;
public class P3483_Unique3DigitEvenNumbers {
    class Solution {
    public int totalNumbers(int[] digits) {
        HashSet<Integer> numbers = new HashSet<>();
        int n = digits.length;
        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    if (digits[k] % 2 != 0) {
                        continue;
                    }
                    int number =digits[i] * 100 + digits[j] * 10+ digits[k];
                    numbers.add(number);
                }
            }
        }
        return numbers.size();
    }
}
}
