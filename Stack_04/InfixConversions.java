/*
Assumptions:
- Input is a valid infix expression with single-digit operands.
- Expression may contain (), +, -, *, / and spaces.

Example:
Input: (a+b)*(c-d)
Output:
Prefix: *+ab-cd
Postfix: ab+cd-*

Time Complexity: O(n)
- Each character is processed once.
- Each operator is pushed and popped at most once.

Space Complexity: O(n)
- Stacks (pre, post, operators) store up to n elements in worst case.
*/
package Stack_04;
import java.util.*;
import java.io.*;

public class InfixConversions {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();

        Stack<String> pre = new Stack<>();
        Stack<String> post = new Stack<>();
        Stack<Character> optors = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if (ch == ' ') continue;

            // operand
            if (Character.isLetterOrDigit(ch)) {
                pre.push(ch + "");
                post.push(ch + "");
            }

            // opening bracket
            else if (ch == '(') {
                optors.push(ch);
            }

            // closing bracket
            else if (ch == ')') {
                while (optors.peek() != '(') {
                    process(pre, post, optors);
                }
                optors.pop(); // remove '('
            }

            // operator
            else {
                while (!optors.isEmpty() &&
                       optors.peek() != '(' &&
                       precedence(optors.peek()) >= precedence(ch)) {
                    process(pre, post, optors);
                }
                optors.push(ch);
            }
        }

        // remaining operators
        while (!optors.isEmpty()) {
            process(pre, post, optors);
        }

        System.out.println("Prefix: " + pre.peek());
        System.out.println("Postfix: " + post.peek());
    }

    public static void process(Stack<String> pre, Stack<String> post, Stack<Character> optors) {
        char op = optors.pop();

        String pre2 = pre.pop();
        String pre1 = pre.pop();
        pre.push(op + pre1 + pre2);

        String post2 = post.pop();
        String post1 = post.pop();
        post.push(post1 + post2 + op);
    }

    public static int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        else return 2;
    }
}