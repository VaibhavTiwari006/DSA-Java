package Leetcode;

public class P2265_CountNodesEqualToAverageofSubtree {

    // Binary Tree Node
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class Solution {

        int answer = 0;

        public int averageOfSubtree(TreeNode root) {
            dfs(root);
            return answer;
        }

        private int[] dfs(TreeNode root) {

            // Base case: no node
            if (root == null) {
                return new int[]{0, 0};
            }

            // Get sum and count from left subtree
            int[] left = dfs(root.left);

            // Get sum and count from right subtree
            int[] right = dfs(root.right);

            // Current subtree sum
            int sum = root.val + left[0] + right[0];

            // Current subtree node count
            int count = 1 + left[1] + right[1];

            // Check if node value equals subtree average
            if (sum / count == root.val) {
                answer++;
            }

            // {subtree sum, subtree node count}
            return new int[]{sum, count};
        }
    }

    public static void main(String[] args) {

        // Tree:
        //
        //         4
        //        / \
        //       8   5
        //      / \   \
        //     0   1   6

        TreeNode root = new TreeNode(4);

        root.left = new TreeNode(8);
        root.right = new TreeNode(5);

        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(1);

        root.right.right = new TreeNode(6);

        Solution solution = new Solution();

        int result = solution.averageOfSubtree(root);

        System.out.println("Answer: " + result);
    }
}