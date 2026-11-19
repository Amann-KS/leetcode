class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int changeTwoLargest = nums[n - 3] - nums[0];
        int changeTwoSmallest = nums[n - 1] - nums[2];
        int changeOneEach = nums[n - 2] - nums[1];

        int minHigh = Math.min(changeTwoLargest, Math.min(changeTwoSmallest, changeOneEach));
        return minHigh; 
    }
}