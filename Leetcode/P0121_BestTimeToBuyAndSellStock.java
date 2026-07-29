package Leetcode;
public class P0121_BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int minValue = prices[0], profit =0;
        for(int i = 1 ; i <= prices.length; i++){
            if(prices[i] < minValue){
                minValue = prices[i];
            }
            if(prices[i] > minValue && prices[i] > profit){
                profit = prices[i] - minValue;
            }
        }
        return profit;
    }
}
