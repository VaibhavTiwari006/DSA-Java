package Basics_01;
import java.util.Scanner;
public class GCD_of_ThreeNumbers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter first , second and third number ");
        int num1= in.nextInt();
        int num2= in.nextInt();
        int num3= in.nextInt();
        while(num2!=0)
        {
            int rem = num1 % num2;
            num1 = num2 ;
            num2 = rem;
        }
        while(num3!=0){
            int rem = num1% num3;
            num1 = num3;
            num3 = rem;
        }
        System.out.println(num1);
        in.close();
    }
}
