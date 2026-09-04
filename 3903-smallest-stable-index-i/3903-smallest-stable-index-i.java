class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] minSuffix = new int[n];
        
        minSuffix[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minSuffix[i] = Math.min(nums[i], minSuffix[i + 1]);
        }
        
        int runningMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            runningMax = Math.max(runningMax, nums[i]);
            if (runningMax - minSuffix[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}