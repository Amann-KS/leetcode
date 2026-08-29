class Solution {
    public int minPatches(int[] nums, long n) {
        int patches = 0;
        int i = 0;
        long miss = 1;

        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i];
                i++;
            } else {
                miss += miss;
                patches++;
            }
        }

        return patches;
    }
}