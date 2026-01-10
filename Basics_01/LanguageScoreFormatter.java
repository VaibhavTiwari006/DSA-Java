package Basics_01;
 import java.util.Scanner;
public class LanguageScoreFormatter {
    public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            String [] words = new String[3];
            int [] num = new int[3];
            for(int i=0;i<3;i++){
                words[i]=sc.next();
                num[i]=sc.nextInt();
            }
             System.out.println("================================");
            for(int i=0;i<3;i++){
               System.out.printf("%-15s%03d%n", words[i], num[i]);
            }
            System.out.println("================================");
            sc.close();
    }
}