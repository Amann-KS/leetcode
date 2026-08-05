class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < invocations.length; i++) {
            graph.get(invocations[i][0]).add(invocations[i][1]);
        }
        boolean[] isSuspicious = new boolean[n];
        dfs(k, graph, isSuspicious);

        boolean canRemove = true;
        for (int i = 0; i < invocations.length; i++) {
            int u = invocations[i][0];
            int v = invocations[i][1];
            if (!isSuspicious[u] && isSuspicious[v]) {
                canRemove = false;
                break;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!canRemove || !isSuspicious[i]) {
                result.add(i);
            }
        }

        return result;
    }

    private void dfs(int u, List<List<Integer>> graph, boolean[] isSuspicious) {
        isSuspicious[u] = true;
        List<Integer> neighbors = graph.get(u);
        for (int i = 0; i < neighbors.size(); i++) {
            int v = neighbors.get(i);
            if (!isSuspicious[v]) {
                dfs(v, graph, isSuspicious);
            }
        }
    }
}