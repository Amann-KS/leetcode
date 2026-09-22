class Solution {
    static class Node {
        int prod;
        long[] count;

        Node(int k) {
            this.prod = 1;
            this.count = new long[k];
        }
    }

    private int k;
    private Node[] tree;
    private int n;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        this.n = nums.length;
        this.tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] result = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int idx = queries[q][0];
            int val = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            update(1, 0, n - 1, idx, val);

            Node resNode = query(1, 0, n - 1, start, n - 1);
            result[q] = (int) resNode.count[x];
        }

        return result;
    }

    private Node merge(Node left, Node right) {
        Node parent = new Node(k);
        parent.prod = (left.prod * right.prod) % k;

        
        for (int r = 0; r < k; r++) {
            parent.count[r] += left.count[r];
        }

       
        for (int r = 0; r < k; r++) {
            int newRem = (left.prod * r) % k;
            parent.count[newRem] += right.count[r];
        }

        return parent;
    }

    private void build(int node, int start, int end, int[] nums) {
        tree[node] = new Node(k);
        if (start == end) {
            int val = nums[start] % k;
            tree[node].prod = val;
            tree[node].count[val] = 1;
            return;
        }

        int mid = start + (end - start) / 2;
        build(2 * node, start, mid, nums);
        build(2 * node + 1, mid + 1, end, nums);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            int rem = val % k;
            tree[node].prod = rem;
            for (int r = 0; r < k; r++) {
                tree[node].count[r] = 0;
            }
            tree[node].count[rem] = 1;
            return;
        }

        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private Node query(int node, int start, int end, int l, int r) {
        if (l <= start && end <= r) {
            return tree[node];
        }

        int mid = start + (end - start) / 2;
        if (r <= mid) {
            return query(2 * node, start, mid, l, r);
        }
        if (l > mid) {
            return query(2 * node + 1, mid + 1, end, l, r);
        }

        Node leftNode = query(2 * node, start, mid, l, r);
        Node rightNode = query(2 * node + 1, mid + 1, end, l, r);
        return merge(leftNode, rightNode);
    }
}