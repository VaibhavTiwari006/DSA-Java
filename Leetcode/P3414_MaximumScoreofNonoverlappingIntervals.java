package Leetcode;
import java.util.*;
public class P3414_MaximumScoreofNonoverlappingIntervals {
    class Solution {
    int n;
    int[] nextIdx;
    Node[][] dp;
    static class Node {
        long score;
        List<Integer> idxs;
        Node() {
            score = 0;
            idxs = new ArrayList<>();
        }
    }
    private int findNext(int[][] intervals, int end) {
        int low = 0;
        int high = n;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (intervals[mid][0] > end) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

       private Node better(Node skip, Node take) {
        if (skip.score > take.score) {
            return skip;
        }

        if (take.score > skip.score) {
            return take;
        }

        int size = Math.min(skip.idxs.size(), take.idxs.size());

        for (int i = 0; i < size; i++) {
            int a = skip.idxs.get(i);
            int b = take.idxs.get(i);

            if (a < b) {
                return skip;
            }

            if (a > b) {
                return take;
            }
        }

        return skip.idxs.size() <= take.idxs.size() ? skip : take;
    }

    public int[] maximumWeight(int[][] intervals) {
        n = intervals.length;

        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals[i][0];
            arr[i][1] = intervals[i][1];
            arr[i][2] = intervals[i][2];
            arr[i][3] = i;
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        nextIdx = new int[n];

        for (int i = 0; i < n; i++) {
            nextIdx[i] = findNext(arr, arr[i][1]);
        }

        dp = new Node[n + 1][5];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = new Node();
        }

        for (int k = 1; k <= 4; k++) {
            dp[n][k] = new Node();
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {
                Node skip = dp[i + 1][k];

                int j = nextIdx[i];
                Node temp = dp[j][k - 1];

                Node take = new Node();
                take.score = temp.score + arr[i][2];

                take.idxs = new ArrayList<>(temp.idxs);
                take.idxs.add(arr[i][3]);
                Collections.sort(take.idxs);

                dp[i][k] = better(skip, take);
            }
        }
        List<Integer> indices = dp[0][4].idxs;
        int[] answer = new int[indices.size()];

        for (int i = 0; i < indices.size(); i++) {
            answer[i] = indices.get(i);
        }
        return answer;
    }
}
}