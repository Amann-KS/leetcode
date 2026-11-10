class Solution {
    public int climbStairs(int n, int[] costs) {
      
        int[] dp = new int[n + 1];
        
      
        java.util.Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 0; i <= n; i++) {
            if (dp[i] == Integer.MAX_VALUE) continue;
          
            for (int step = 1; step <= 3; step++) {
                int j = i + step;
                if (j <= n) {
                    
                    int jumpCost = costs[j - 1] + (j - i) * (j - i);
                    dp[j] = Math.min(dp[j], dp[i] + jumpCost);
                }
            }
        }
        
        return dp[n];
    }
}