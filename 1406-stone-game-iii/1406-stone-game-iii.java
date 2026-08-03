class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            int currentSum = 0;
            int maxDiff = Integer.MIN_VALUE;

            for (int k = 1; k <= 3 && i + k <= n; k++) {
                currentSum += stoneValue[i + k - 1];
                maxDiff = Math.max(maxDiff, currentSum - dp[i + k]);
            }

            dp[i] = maxDiff;
        }
        if (dp[0] > 0) return "Alice";
        if (dp[0] < 0) return "Bob";
        return "Tie";
    }
}