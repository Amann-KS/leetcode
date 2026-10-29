


class Solution {

    class Fenwick {
        int n;
        int[] tree;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void update(int index, int value) {
            for (index++; index <= n; index += index & -index) {
                tree[index] ^= value;
            }
        }

        int query(int index) {
            int result = 0;
            for (; index > 0; index -= index & -index) {
                result ^= tree[index];
            }
            return result;
        }
    }

    List<Integer>[] graph;
    int[] parent, depth, tin, tout, bit;
    int[][] up;
    int timer, log;

    void dfs(int u, int p) {
        parent[u] = (p == -1) ? 0 : p;
        tin[u] = timer++;

        for (int v : graph[u]) {
            if (v != p) {
                depth[v] = depth[u] + 1;
                dfs(v, u);
            }
        }

        tout[u] = timer;
    }

    int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; diff > 0; j++, diff >>= 1) {
            if ((diff & 1) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) return u;

        for (int j = log - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return parent[u];
    }

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        parent = new int[n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        timer = 0;

        dfs(0, -1);

        log = 32 - Integer.numberOfLeadingZeros(n);
        up = new int[log][n];

        System.arraycopy(parent, 0, up[0], 0, n);

        for (int j = 1; j < log; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        bit = new int[n];

        for (int i = 0; i < n; i++) {
            bit[i] = 1 << (s.charAt(i) - 'a');
        }

        Fenwick fw = new Fenwick(n);

        for (int i = 0; i < n; i++) {
            fw.update(tin[i], bit[i]);
            fw.update(tout[i], bit[i]);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            int u = Integer.parseInt(parts[1]);

            if (parts[0].equals("update")) {
                int newBit = 1 << (parts[2].charAt(0) - 'a');

                if (bit[u] != newBit) {
                    int delta = bit[u] ^ newBit;

                    fw.update(tin[u], delta);
                    fw.update(tout[u], delta);

                    bit[u] = newBit;
                }
            } else {
                int v = Integer.parseInt(parts[2]);
                int w = lca(u, v);

                int mask = fw.query(tin[u] + 1)
                         ^ fw.query(tin[v] + 1)
                         ^ bit[w];

                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }
}
