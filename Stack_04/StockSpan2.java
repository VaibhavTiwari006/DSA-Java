/*
Program: Stock Span Problem
 
  Description:
  This program calculates the stock span for each day based on given stock prices.
  The span of a stock’s price on a given day is defined as the maximum number of
  consecutive days (including the current day) for which the price of the stock
  was less than or equal to today’s price.
 
  Approach:
  - Uses a Monotonic Stack storing indices.
  - Traverse the array from left to right.  
  - For each element:
      1. Pop indices of all elements smaller than or equal to current price.
      2. If stack is empty → span = i + 1
      3. Else → span = i - index of previous greater element
      4. Push current index onto the stack.
 
 Time Complexity: O(n)
 Space Complexity: O(n)
 
  Example:
 Input:  [100, 80, 60, 70, 60, 75, 85]
 Output: [1, 1, 1, 2, 1, 4, 6]
 */
package Stack_04;
import java.util.*;

public class StockSpan2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the number of elements");
        int len = in.nextInt();

        int arr[] = new int[len];
        int span[] = new int[len];

        Stack<Integer> st = new Stack<>(); // store indices

        System.out.println("Enter the elements ");
        for (int i = 0; i < len; i++) {
            arr[i] = in.nextInt();
        }
        st.push(0);
        span[0] = 1;

        for (int i = 1; i < len; i++) {

            while (!st.isEmpty() && arr[i] >= arr[st.peek()]) {
                st.pop();
            }

            if (st.isEmpty()) {
                span[i] = i + 1;
            } else {
                span[i] = i - st.peek();
            }

            st.push(i);
        }
        System.out.println("The Stock span of each element is:");
        for (int val : span) {
            System.out.print(val + " ");
        }
        in.close();
    }
}