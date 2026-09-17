package Leetcode;

public class P1477_FindTwoNonoverlappingSubarraysEachWithTargetSum {

    class Solution {

        public int minSumOfLengths(int[] arr, int target) {

            int n = arr.length;

            int[] best = new int[n];

            int left = 0;
            int sum = 0;

            int shortest = Integer.MAX_VALUE;
            int answer = Integer.MAX_VALUE;

            for (int right = 0; right < n; right++) {

                sum += arr[right];

                while (sum > target) {
                    sum -= arr[left];
                    left++;
                }

                if (sum == target) {

                    int currentLength = right - left + 1;

                    if (left > 0 &&
                        best[left - 1] != Integer.MAX_VALUE) {

                        answer = Math.min(
                            answer,
                            currentLength + best[left - 1]
                        );
                    }

                    shortest = Math.min(
                        shortest,
                        currentLength
                    );
                }

                best[right] = shortest;
            }

            if (answer == Integer.MAX_VALUE) {
                return -1;
            }

            return answer;
        }
    }
}