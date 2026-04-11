package Stack_04;
/*
Program: Infix Expression Evaluation using Stacks

Description:
This program evaluates a given infix expression using two stacks:
1. Operand stack (for numbers)
2. Operator stack (for operators)

Assumptions:
- The input expression is valid and properly parenthesized.
- The expression contains only:
  digits (0–9), operators (+, -, *, /), and parentheses.
- Only **single-digit numbers** are supported.
- Division is integer division.
- No invalid characters are present.
- No division by zero occurs.


Input: "2+(5*3)-6/2"

Output: 14

Time Complexity" O(n)

Space Complexity: O(n)
*/
import java.io.*;
import java.util.*;

public class InfixEvaluation {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();

        Stack<Integer> opnds = new Stack<>();
        Stack<Character> optors = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            // ignore spaces
            if (ch == ' ') {
                continue;
            }
            else if (ch == '(') {
                optors.push(ch);
            }
            else if (Character.isDigit(ch)) {
                opnds.push(ch - '0');
            }
            else if (ch == ')') {
                while (optors.peek() != '(') {
                    int v2 = opnds.pop();
                    int v1 = opnds.pop();
                    char optor = optors.pop();

                    int result = operation(v1, v2, optor);
                    opnds.push(result);
                }
                optors.pop(); // remove '('
            }
            else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (optors.size() > 0 &&
                       optors.peek() != '(' &&
                       precedence(ch) <= precedence(optors.peek())) {

                    int v2 = opnds.pop();
                    int v1 = opnds.pop();
                    char optor = optors.pop();

                    int result = operation(v1, v2, optor);
                    opnds.push(result);
                }
                optors.push(ch);
            }
        }

        // final evaluation
        while (optors.size() != 0) {
            int v2 = opnds.pop();
            int v1 = opnds.pop();
            char optor = optors.pop();

            int result = operation(v1, v2, optor);
            opnds.push(result);
        }

        System.out.println(opnds.peek());
    }

    public static int precedence(char optor) {
        if (optor == '+' || optor == '-') {
            return 1;
        } 
        else {
            return 2; // * and /
        }
    }

    public static int operation(int v1, int v2, char optor) {
        if (optor == '+') return v1 + v2;
        else if (optor == '-') return v1 - v2;
        else if (optor == '*') return v1 * v2;
        else return v1 / v2;
    }
}