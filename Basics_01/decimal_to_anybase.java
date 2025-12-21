package Basics_01;
import java.util.Scanner;
public class decimal_to_anybase {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the decimal number ");
        int n=in.nextInt();
        System.out.println("Enter the base");
        int b=in.nextInt();
        int dn = getValueInBase(n,b);
        System.out.println(dn);
        in.close();
    }
    public static int getValueInBase(int n,int b){
        int rv =0,p=1;
        while(n>0){
            int dig = n%b;
            n = n/b;
            rv+=dig*p;
            p=p*10;
        }
        return rv;
    }
}