package Stack_04;
import java.util.*;

public class MinimumStack1 {

    public static class MinStack {

        Stack<Integer> allData;
        Stack<Integer> minData;

        public MinStack() {
            allData = new Stack<>();
            minData = new Stack<>();
        }

        int size() {
            return allData.size();
        }

        void push(int val) {
            allData.push(val);

            if (minData.size() == 0 || val <= minData.peek()) {
                minData.push(val);
            }
        }

        int pop() {

            if (size() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            } else {

                int val = allData.pop();

                if (val == minData.peek()) {
                    minData.pop();
                }

                return val;
            }
        }

        int top() {

            if (size() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            } else {
                return allData.peek();
            }
        }

        int min() {

            if (size() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            } else {
                return minData.peek();
            }
        }
    }

    public static void main(String[] args) {

        MinStack st = new MinStack();

        st.push(10);
        st.push(20);
        st.push(5);
        st.push(7);

        System.out.println("Top Element: " + st.top());
        System.out.println("Minimum Element: " + st.min());

        System.out.println("Popped Element: " + st.pop());

        System.out.println("Top Element After Pop: " + st.top());
        System.out.println("Minimum Element After Pop: " + st.min());

        System.out.println("Current Size: " + st.size());
    }
}