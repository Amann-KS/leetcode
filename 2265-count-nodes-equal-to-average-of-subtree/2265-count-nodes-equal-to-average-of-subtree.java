class Solution {
    private int matchingNodeCount = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return matchingNodeCount;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0}; 
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int totalSum = left[0] + right[0] + node.val;
        int totalCount = left[1] + right[1] + 1;

        if (totalSum / totalCount == node.val) {
            matchingNodeCount++;
        }

        return new int[]{totalSum, totalCount};
    }
}