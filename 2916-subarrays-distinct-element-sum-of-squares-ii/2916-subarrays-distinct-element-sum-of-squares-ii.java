class Solution {
    public int sumCounts(int[] nums) {
        int n = nums.length;
        long MOD = 1_000_000_007;

    
        long[] sum = new long[4 * n];
        long[] sqSum = new long[4 * n];
        long[] lazy = new long[4 * n];

        java.util.Map<Integer, Integer> lastSeen = new java.util.HashMap<>();
        
        long totalSum = 0;
        long currentSum = 0;

        for (int i = 0; i < n; i++) {
            int prev = lastSeen.getOrDefault(nums[i], -1);
      
            update(1, 0, n - 1, prev + 1, i, 1, sum, sqSum, lazy);
            lastSeen.put(nums[i], i);

          
            totalSum = (totalSum + sqSum[1]) % MOD;
        }

        return (int) totalSum;
    }

    private void pushDown(int node, int l, int r, long[] sum, long[] sqSum, long[] lazy) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            apply(2 * node, l, mid, lazy[node], sum, sqSum, lazy);
            apply(2 * node + 1, mid + 1, r, lazy[node], sum, sqSum, lazy);
            lazy[node] = 0;
        }
    }

    private void apply(int node, int l, int r, long val, long[] sum, long[] sqSum, long[] lazy) {
        long len = (r - l + 1);
       
        sqSum[node] = (sqSum[node] + 2 * val * sum[node] + val * val * len) % 1_000_000_007;
        sum[node] = (sum[node] + val * len) % 1_000_000_007;
        lazy[node] = (lazy[node] + val) % 1_000_000_007;
    }

    private void update(int node, int l, int r, int ql, int qr, long val, long[] sum, long[] sqSum, long[] lazy) {
        if (ql <= l && r <= qr) {
            apply(node, l, r, val, sum, sqSum, lazy);
            return;
        }
        pushDown(node, l, r, sum, sqSum, lazy);
        int mid = (l + r) / 2;
        if (ql <= mid) {
            update(2 * node, l, mid, ql, qr, val, sum, sqSum, lazy);
        }
        if (qr > mid) {
            update(2 * node + 1, mid + 1, r, ql, qr, val, sum, sqSum, lazy);
        }
        sum[node] = (sum[2 * node] + sum[2 * node + 1]) % 1_000_000_007;
        sqSum[node] = (sqSum[2 * node] + sqSum[2 * node + 1]) % 1_000_000_007;
    }
}