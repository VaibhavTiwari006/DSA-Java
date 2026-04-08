package Stack_04;
import java.util.*;
public class NGETR_1 {
   // This program computes the Next Greater Element (NGE) for each element in an array.
   // This is done using beginner friendly approach
    public static void main(String args[]){
        Scanner in  = new Scanner(System.in);
        Stack <Integer> st = new Stack<>();
        System.out.println("Enter number of element in stack");
        int num = in.nextInt();
        int arr[] = new int[num];
        int nge[] = new int[num];
        System.out.println("Enter elements into the stack");
        for(int i =0 ; i < num; i++){
            arr[i] = in.nextInt();
        }
         st.push(arr[arr.length-1]);
         nge[arr.length-1]=-1;
         for(int i= arr.length-2; i >=0 ; i-- ){
            while(st.size()>0 && arr[i] >= st.peek()){
                st.pop();
            }
            if(st.size()==0) {
                nge[i]=-1;
            }
            else{
                nge[i]=st.peek();
            } 
            st.push(arr[i]);

         }
         System.out.println("The resulted stack is ");
         for(int i =0 ; i < num; i++){
            System.out.println(nge[i]);
        }
        in.close();
    }
}