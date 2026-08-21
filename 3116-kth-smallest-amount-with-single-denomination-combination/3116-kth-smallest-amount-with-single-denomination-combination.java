class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long low = 1;
        long minCoin = coins[0];
        for (int c : coins) {
            minCoin = Math.min(minCoin, c);
        }
        long high = minCoin * k;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (countAmounts(coins, mid) >= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private long countAmounts(int[] coins, long target) {
        int n = coins.length;
        long totalCount = 0;

       
        for (int mask = 1; mask < (1 << n); mask++) {
            long currentLcm = 1;
            int bitCount = 0;

            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1) {
                    bitCount++;
                    currentLcm = lcm(currentLcm, coins[i]);
                 
                    if (currentLcm > target) break;
                }
            }

            if (currentLcm <= target) {
                long count = target / currentLcm;
                if (bitCount % 2 == 1) {
                    totalCount += count;
                } else {
                    totalCount -= count;
                }
            }
        }

        return totalCount;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}