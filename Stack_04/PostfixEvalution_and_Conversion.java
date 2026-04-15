package Stack_04;

import java.io.*;
import java.util.*;

public class PostfixEvalution_and_Conversion {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();

        Stack<String> infix = new Stack<>();
        Stack<String> pre = new Stack<>();
        Stack<Integer> evaluation = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {

                int v2 = evaluation.pop();
                int v1 = evaluation.pop();
                int val = operation(v1, v2, ch);
                evaluation.push(val);

                String infixv2 = infix.pop();
                String infixv1 = infix.pop();
                String infixval = "(" + infixv1 + ch + infixv2 + ")";
                infix.push(infixval);

                String prev2 = pre.pop();
                String prev1 = pre.pop();
                String preval = ch + prev1 + prev2;
                pre.push(preval);

            } else {
                evaluation.push(ch - '0');
                infix.push(ch + "");
                pre.push(ch + "");
            }
        }

        System.out.println(evaluation.pop());
        System.out.println(infix.pop());
        System.out.println(pre.pop());
    }

    public static int operation(int v1, int v2, char op) {
        if (op == '+') return v1 + v2;
        else if (op == '-') return v1 - v2;
        else if (op == '*') return v1 * v2;
        else return v1 / v2;
    }
}