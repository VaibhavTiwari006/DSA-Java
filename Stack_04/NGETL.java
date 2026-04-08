// Next Greater Element to the Left (NGETL)
// Traverses left to right using a stack to find the first greater element
// on the left side for each element. Returns -1 if no greater element exists.
package Stack_04;
import java.util.*;
public class NGETL {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of elements");
        int len = in.nextInt();
        int arr[] = new int[len];
        int nge[] = new int[len];
        Stack<Integer> st = new Stack<>();
        System.out.println("Enter the elements ");
        for (int i = 0; i < len; i++) {
            arr[i] = in.nextInt();
        }
        // core logic
        st.push(arr[0]);
        nge[0] = -1;
        for (int i = 1; i < len; i++) {
            while (st.size() > 0 && arr[i] >= st.peek()) {
                st.pop();
            }
            if (st.size() == 0) {
                nge[i] = -1;
            } else {
                nge[i] = st.peek();
            }
            st.push(arr[i]);
        }

        System.out.println("The Next Greater element to the left is :");
        for (int i = 0; i < len; i++) {
            System.out.print(nge[i] + " ");
        }
        in.close();
    }
}