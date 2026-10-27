package Leetcode;
import java.util.*;
public class P0682_BaseballGame {
class Solution {
    public int calPoints(String[] operations) {
        ArrayList<Integer> list = new ArrayList<>();

        for (String op : operations) {
            int n = list.size();

            if (op.equals("C")) {
                list.remove(n - 1);
            } 
            else if (op.equals("D")) {
                list.add(2 * list.get(n - 1));
            } 
            else if (op.equals("+")) {
                list.add(list.get(n - 1) + list.get(n - 2));
            } 
            else {
                list.add(Integer.parseInt(op));
            }
        }
        int sum = 0;
        for (int score : list) {
            sum += score;
        }
        return sum;
    }
}
}