class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        Arrays.sort(nums);
        
        List<Integer> result = new ArrayList<Integer>();
        int n = nums.length;
        
        for (int i = 0; i < n - 1; i = i + 1) {
            int currentElement = nums[i];
            int nextElement = nums[i + 1];
            
            if (nextElement > currentElement + 1) {
                for (int target = currentElement + 1; target < nextElement; target = target + 1) {
                    result.add(target);
                }
            }
        }
        
        return result;
    }
}
