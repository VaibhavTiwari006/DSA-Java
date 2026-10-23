package Stack_04;
import java.util.*;

public class Balance_Paranthesis {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = 0;
        System.out.println("Enter string of brackets");
        String expression = in.nextLine();
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '(') {
                st.push(ch);
            } else if (ch == ')') {
                if (st.isEmpty()) {
                    System.out.println(-1);
                    in.close();
                    return;
                }
                st.pop();
                count++;
            }
        }
        if (!st.isEmpty()) {
            System.out.println("-1");
        } else {
            System.out.println(count);

        }
        in.close();
    }
}