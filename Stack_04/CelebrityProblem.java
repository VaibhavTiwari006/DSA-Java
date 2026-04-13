/*
Celebrity Problem (Stack Based)

Description:
Find the celebrity in a group. A celebrity is known by everyone but knows no one.

Approach:
1. Push all people (0 to n-1) into a stack.
2. Pop two at a time and eliminate one based on "knows" relation.
3. Last remaining person is potential celebrity.
4. Verify:
   - Everyone knows them
   - They know no one

Time Complexity: O(n)
Space Complexity: O(n)

Sample Input:
4
0111
0011
0000
0010

Sample Output:
2

If no celebrity exists:
Output: Nobody is the celebrity
*/
package Stack_04;

import java.util.*;
import java.io.*;

public class CelebrityProblem {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the order of matrix");
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        for (int j = 0; j < n; j++) {
            String line = br.readLine();
            for (int k = 0; k < n; k++) {
                //Converts each character '0'/'1' → integer 0/1
                arr[j][k] = line.charAt(k) - '0';
            }
        }
        findCelebrity(arr);
    }

    public static void findCelebrity(int[][] arr) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            st.push(i); 
        }
        //Elimination Process
        while (st.size() >= 2) {
            int i = st.pop();
            int j = st.pop();
            if (arr[i][j] == 1) {
                // if i knows j -> i is not a celebrity
                st.push(j);
            } else {
                // if i does not know j -> j is not a celebrity
                st.push(i);
            }
        }
        int pot = st.pop();
        for (int i = 0; i < arr.length; i++) {
            if (i != pot) {
                if (arr[i][pot] == 0 || arr[pot][i] == 1) {
                    System.out.print("Nobody is the celebrity");
                    return;
                }
            }
        }
        System.out.print(pot);
    }

}
