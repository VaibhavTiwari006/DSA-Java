
package Basics_01;

public class NewNumber {
    public static void main(String[] s) {
        System.out.println(NewNumber(2565));
    }

    static int NewNumber(int n) {
        String newNumber = "9876543210";
        String s = String.valueOf(n);
        String newS = "";

        for (int i = 0; i < s.length(); i++) {
            newS += newNumber.indexOf(s.charAt(i));
        }

        return Integer.parseInt(newS);
    }
}
