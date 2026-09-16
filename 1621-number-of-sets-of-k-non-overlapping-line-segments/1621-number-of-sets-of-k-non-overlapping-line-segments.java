class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007;
        
        long totalN = n + k - 1;
        long totalK = 2 * k;
        
        if (totalN < totalK) return 0;
        
    
        long numerator = 1;
        long denominator = 1;
        
        for (int i = 1; i <= totalK; i++) {
            numerator = (numerator * (totalN - i + 1)) % MOD;
            denominator = (denominator * i) % MOD;
        }
        
        return (int) (numerator * modInverse(denominator, MOD) % MOD);
    }
    
    private long modInverse(long base, long mod) {
        return power(base, mod - 2, mod);
    }
    
    private long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp /= 2;
        }
        return res;
    }
}