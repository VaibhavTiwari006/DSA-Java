package Stack_04;

import java.util.Stack;

public class MinimumStack2 {

    public static class MinStack {

        Stack<Integer> data;
        int min;

        public MinStack() {
            data = new Stack<>();
        }

        int size() {
            return data.size();
        }

        void push(int val) {

            if (data.size() == 0) {
                data.push(val);
                min = val;
            } 
            else if (val >= min) {
                data.push(val);
            } 
            else {
                // store encoded value
                data.push(val + val - min);
                min = val;
            }
        }

        int pop() {

            if (size() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            }

            int top = data.pop();

            if (top >= min) {
                return top;
            } 
            else {
                int originalMin = min;
                min = 2 * min - top;
                return originalMin;
            }
        }

        int top() {

            if (size() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            }

            int top = data.peek();

            if (top >= min) {
                return top;
            } 
            else {
                return min;
            }
        }

        int min() {

            if (size() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            }

            return min;
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