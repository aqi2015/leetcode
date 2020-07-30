//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。 
//
// 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 示例 1: 
//
// 输入: [2,4,1], k = 2
//输出: 2
//解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
// 
//
// 示例 2: 
//
// 输入: [3,2,6,5,0,3], k = 2
//输出: 7
//解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
//     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 
//。
// 
// Related Topics 动态规划 
// 👍 257 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {
    public int maxProfit(int k, int[] prices) {
//    public static int[] maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k <= 0 || len == 0 || len == 1) return 0;
//        if (k <= 0 || prices.length == 0 || prices.length == 1) return new int[0];
        if (k > len/2) return quickSolve(prices);
        int[] holds = new int[k];
        int[] releases = new int[k];
        Arrays.fill(holds, Integer.MIN_VALUE);
        Arrays.fill(releases, 0);


        for (int i = 0; i < len; i++) {
            for (int j = k-1; j > 0; j--) {
                releases[j] = Math.max(releases[j], holds[j]+prices[i]);
                holds[j] = Math.max(holds[j], releases[j-1]-prices[i]);
            }
            releases[0] = Math.max(releases[0], holds[0]+prices[i]);
            holds[0] = Math.max(holds[0], -prices[i]);
        }
//        return Arrays.stream(releases).max().getAsInt();
        return releases[releases.length-1];
//        return releases;
    }

    public int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            if (prices[i] > prices[i-1]) profit += prices[i] - prices[i-1];
        return profit;
    }

    public static void main(String[] args) {
        int k = 2;
        int[] prices = new int[]{2,1,2,0,1};
        System.out.println(maxProfit(k, prices));
        for (int i : maxProfit(k, prices)) {
            System.out.println(i);
        }
    }
}

//国外最佳
public class Solution {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) return quickSolve(prices);

        int[][] t = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int tmpMax =  -prices[0];
            for (int j = 1; j < len; j++) {
                t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
                tmpMax =  Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
            }
        }
        return t[k][len - 1];
    }


    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
