package Leetcode;

public class P3525_FindXValueofArrayII {
    class Solution {
        private int k;
        private int[] product;
        private int[][] prefix;

        public int[] resultArray(int[] nums, int k, int[][] queries) {
            this.k = k;
            int n = nums.length;
            product = new int[4 * n];
            prefix = new int[4 * n][k];
            build(1, 0, n - 1, nums);
            int[] answer = new int[queries.length];

            for (int i = 0; i < queries.length; i++) {
                int[] q = queries[i];
                update(1, 0, n - 1, q[0], q[1] % k);
                int[] state = { 1 % k, 0 };
                query(1, 0, n - 1, q[2], q[3], state);
                answer[i] = state[1];
            }
            return answer;
        }

        private void build(int node, int left, int right, int[] nums) {
            if (left == right) {
                product[node] = nums[left] % k;
                prefix[node][product[node]] = 1;
                return;
            }
            int mid = left + (right - left) / 2;
            build(node * 2, left, mid, nums);
            build(node * 2 + 1, mid + 1, right, nums);
            merge(node);
        }

        private void merge(int node) {
            int a = node * 2, b = a + 1;
            product[node] = product[a] * product[b] % k;

            for (int r = 0; r < k; r++) {
                prefix[node][r] = prefix[a][r];
            }
            for (int r = 0; r < k; r++) {
                int remainder = product[a] * r % k;
                prefix[node][remainder] += prefix[b][r];
            }
        }

        private void update(int node, int left, int right, int index, int value) {
            if (left == right) {
                prefix[node][product[node]] = 0;
                product[node] = value;
                prefix[node][value] = 1;
                return;
            }
            int mid = left + (right - left) / 2;
            if (index <= mid) {
                update(node * 2, left, mid, index, value);
            } else {
                update(node * 2 + 1, mid + 1, right, index, value);
            }
            merge(node);
        }

        private void query(int node, int left, int right, int start, int x, int[] state) {
            if (right < start)
                return;

            if (left >= start) {
                for (int r = 0; r < k; r++) {
                    if (state[0] * r % k == x) {
                        state[1] += prefix[node][r];
                    }
                }
                state[0] = state[0] * product[node] % k;
                return;
            }

            int mid = left + (right - left) / 2;
            query(node * 2, left, mid, start, x, state);
            query(node * 2 + 1, mid + 1, right, start, x, state);
        }
    }

}
