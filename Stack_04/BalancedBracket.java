package Stack_04;
import java.util.*;
public class BalancedBracket {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } 
            else if (ch == ')') {
                if (!handleClosing(st, '(')) {
                    System.out.println(false);
                    return;
                }
            } 
            else if (ch == '}') {
                if (!handleClosing(st, '{')) {
                    System.out.println(false);
                    return;
                }
            } 
            else if (ch == ']') {
                if (!handleClosing(st, '[')) {
                    System.out.println(false);
                    return;
                }
            }
        }
        System.out.println(st.size() == 0);
        in.close();
    }
    public static boolean handleClosing(Stack<Character> st, char corresoch) {
        if (st.size() == 0) {
            return false;
        } 
        else if (st.peek() != corresoch) {
            return false;
        } 
        else {
            st.pop();
            return true;
        }
    }
}
