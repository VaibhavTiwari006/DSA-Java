package Stack_04;
/*
Program: Sliding Window Maximum using Next Greater Element (NGE)

Description:
This program finds the maximum element in every sliding window of size k for a given array.

Key Insight:
Instead of checking all k elements in every window, we jump through the
Next Greater Element chain, reducing redundant comparisons.

Time Complexity:
- NGE Preprocessing: O(n)
- Sliding Window Traversal: O(n)
- Overall Complexity: O(n)

Space Complexity:
- O(n) for nge[] and stack

Example:
Input:
arr = [2, 5, 4, 6, 8, 1, 3], k = 3

Output:
5
6
8
8
8

*/
import java.io.*;
import java.util.*;

public class SlidingWindowMaxStack {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int k = Integer.parseInt(br.readLine());

        // code
        // Next Greater Element (NGE) preprocessing
        Stack<Integer> st = new Stack<>();
        int[] nge = new int[arr.length];
        st.push(arr.length - 1);
        nge[arr.length - 1] = arr.length;

        for (int i = arr.length - 2; i >= 0; i--) {
            // -a+
            while (st.size() > 0 && arr[i] >= arr[st.peek()]) {
                st.pop();
            }
            if (st.size() == 0) {
                nge[i] = arr.length;
            } else {
                nge[i] = st.peek();
            }
            st.push(i);
        }
        // Sliding window maximum using NGE
        int j = 0;
        for (int i = 0; i <= arr.length - k; i++) {
            // enter the loop to find the maximum of window starting at 1
            if (j < i) {
                j = i;
            }
            while (nge[j] < i + k) {
                j = nge[j];
            }
            System.out.println(arr[j]);
        }
    }
}