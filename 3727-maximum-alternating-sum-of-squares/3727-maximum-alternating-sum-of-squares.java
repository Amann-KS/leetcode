class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squared = new long[n];
        
        for (int i = 0; i < n; i++) {
            long val = nums[i];
            squared[i] = val * val;
        }
        
        Arrays.sort(squared);
        
        long totalScore = 0;
        int mid = n / 2;

        for (int i = 0; i < mid; i++) {
            totalScore -= squared[i];
        }
       
        for (int i = mid; i < n; i++) {
            totalScore += squared[i];
        }
        
        return totalScore;
    }
}