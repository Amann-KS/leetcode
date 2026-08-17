class Solution {
    private int[][] memo;
    private int[] prefix;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        memo = new int[n][n];
        prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        return solve(0, n - 1);
    }

    private int solve(int i, int j) {
        if (i == j) {
            return 0;
        }

        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int maxScore = 0;
        for (int k = i; k < j; k++) {
            int leftSum = prefix[k + 1] - prefix[i];
            int rightSum = prefix[j + 1] - prefix[k + 1];

            if (leftSum < rightSum) {
                
                maxScore = Math.max(maxScore, leftSum + solve(i, k));
            } else if (rightSum < leftSum) {
    
                maxScore = Math.max(maxScore, rightSum + solve(k + 1, j));
            } else {
        
                int keepLeft = leftSum + solve(i, k);
                int keepRight = rightSum + solve(k + 1, j);
                maxScore = Math.max(maxScore, Math.max(keepLeft, keepRight));
            }
        }

        memo[i][j] = maxScore;
        return maxScore;
    }
}