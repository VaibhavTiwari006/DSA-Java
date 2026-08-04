//Example 2:
//Input: address = "255.100.50.0"
//Output: "255[.]100[.]50[.]0"
package Leetcode;
public class P1108_DefanginAnIPAddress {
     public String defangIPaddr(String address) {
       StringBuilder ans = new StringBuilder();
        for(int i=0; i <address.length(); i++){
            if(address.charAt(i) == '.'){
                 ans.append("[.]");                
            }
            else{
                ans.append(address.charAt(i));
            }
        }
        return ans.toString();
     }
}
