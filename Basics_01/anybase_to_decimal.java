package Basics_01;
import java.util.Scanner;
public class anybase_to_decimal {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number ");
        int n=in.nextInt();
        System.out.println("Enter the base ");
        int b= in.nextInt();
        int value= anybaseToDecimal(n,b);
        System.out.println(value);
        in.close();
    }
    public static int anybaseToDecimal(int n , int b){
        int rv=0,p=1;
        while(n>0){
            int dig = n%10;
            n = n/10;
            rv += dig*p;
            p = p*b;
        }
        return rv;
    }
}

    

