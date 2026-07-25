class Solution {
    public int[] transformArray(int[] nums) {
        int zeros = 0;
        
        for (int num : nums) {
            if (num % 2 == 0) {
                zeros++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (i < zeros) ? 0 : 1;
        }
        
        return nums;
    }
}
