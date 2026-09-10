package Basics_01;
import java.util.*;
public class decimal_to_octal {
    public static void main(String[] args) {
        Scanner in  = new Scanner(System.in);
        System.out.println("Enter the number in decimal");
        int num = in.nextInt();
        int ans =0 , p=1;
        while(num > 0){
            int dig = num % 8;
            num = num / 8;
            ans += dig * p;
            p = p *10;
        }
        System.out.println(ans);
        in.close();
    }
}
