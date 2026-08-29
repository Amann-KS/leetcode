class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);

        int groupIdx = 0;
        Map<Integer, Integer> numToGroup = new HashMap<>();
        Map<Integer, Queue<Integer>> groupToList = new HashMap<>();

        numToGroup.put(sortedNums[0], groupIdx);
        groupToList.put(groupIdx, new LinkedList<>());
        groupToList.get(groupIdx).offer(sortedNums[0]);

      
        for (int i = 1; i < n; i++) {
            if (sortedNums[i] - sortedNums[i - 1] > limit) {
                groupIdx++;
            }
            numToGroup.put(sortedNums[i], groupIdx);
            groupToList.computeIfAbsent(groupIdx, k -> new LinkedList<>()).offer(sortedNums[i]);
        }

     
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int group = numToGroup.get(nums[i]);
            result[i] = groupToList.get(group).poll();
        }

        return result;
    }
}