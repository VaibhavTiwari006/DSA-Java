//Input: command = "G()(al)"
//Output: "Goal"
package Leetcode;
public class P1678_GoalParserInterpretation {
    public String interpret(String command) {  
    StringBuilder ans = new StringBuilder();
    for(int i=0; i<command.length(); i++){
        if(command.charAt(i) == '(' && command.charAt(i+1) ==')'){
            ans.append('o');
            i++;
        }
        else if(command.charAt(i) == '(' && command.charAt(i+1) !=')'){
            ans.append("al");
            i+=4;
        }
        else{
            ans.append(command.charAt(i));
        }
    }
    return ans.toString();
    }
}
