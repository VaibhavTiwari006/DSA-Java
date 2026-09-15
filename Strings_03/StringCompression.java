package Strings_03;

// Input s = "aabccccaaa"
// Output = "a2b1c4a3"
public class StringCompression {
    public static void main(String[] args) {
        String s = "aabccccaaa";
        String ans = "";
        int count = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                ans += s.charAt(i - 1);
                ans += count;
                count = 1;
            }
        }
        ans += s.charAt(s.length() - 1);
        ans += count;
        System.out.println(ans);
    }
}
