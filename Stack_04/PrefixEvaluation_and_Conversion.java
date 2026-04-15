package Stack_04;

import java.io.*;
import java.util.*;

public class PrefixEvalution_and_Conversion {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();

        Stack<String> infix = new Stack<>();
        Stack<String> postfix = new Stack<>();
        Stack<Integer> evaluation = new Stack<>();

        // traverse from right to left
        for (int i = exp.length() - 1; i >= 0; i--) {
            char ch = exp.charAt(i);

            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {

                int v1 = evaluation.pop();
                int v2 = evaluation.pop();
                int val = operation(v1, v2, ch);
                evaluation.push(val);

                String infixv1 = infix.pop();
                String infixv2 = infix.pop();
                String infixval = "(" + infixv1 + ch + infixv2 + ")";
                infix.push(infixval);

                String postv1 = postfix.pop();
                String postv2 = postfix.pop();
                String postval = postv1 + postv2 + ch;
                postfix.push(postval);

            } else {
                evaluation.push(ch - '0');
                infix.push(ch + "");
                postfix.push(ch + "");
            }
        }

        System.out.println(evaluation.pop());
        System.out.println(infix.pop());
        System.out.println(postfix.pop());
    }

    public static int operation(int v1, int v2, char op) {
        if (op == '+') return v1 + v2;
        else if (op == '-') return v1 - v2;
        else if (op == '*') return v1 * v2;
        else return v1 / v2;
    }
}