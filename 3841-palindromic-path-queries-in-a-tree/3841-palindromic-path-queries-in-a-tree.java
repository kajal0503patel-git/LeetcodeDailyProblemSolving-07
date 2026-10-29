import java.util.*;

class Solution {
    private int[] bit;
    private int N;

    private void bitUpdate(int i, int val) {
        for (i++; i <= N + 1; i += i & -i) bit[i] ^= val;
    }

    private int bitQuery(int i) {
        int res = 0;
        for (i++; i > 0; i -= i & -i) res ^= bit[i];
        return res;
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        N = n;
        bit = new int[n + 3];

        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int LOG = 1;
        while ((1 << LOG) < n) LOG++;
        int[][] up = new int[LOG + 1][n];
        int[] depth = new int[n];
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] mask = new int[n];
        int[] pre = new int[n];

        for (int i = 0; i < n; i++) mask[i] = 1 << (s.charAt(i) - 'a');

        // Iterative DFS from root 0
        int[] stack = new int[n];
        int[] idx = new int[n];
        int sp = 0, timer = 0;
        stack[sp++] = 0;
        up[0][0] = 0;
        pre[0] = mask[0];
        tin[0] = timer++;

        while (sp > 0) {
            int u = stack[sp - 1];
            if (idx[u] < adj[u].size()) {
                int v = adj[u].get(idx[u]++);
                if (u != 0 && v == up[0][u]) continue;
                if (v == 0) continue; // root is never a child
                up[0][v] = u;
                depth[v] = depth[u] + 1;
                pre[v] = pre[u] ^ mask[v];
                tin[v] = timer++;
                stack[sp++] = v;
            } else {
                tout[u] = timer - 1;
                sp--;
            }
        }

        // Binary lifting table
        for (int j = 1; j <= LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        // Build BIT: point value at tin[v] should equal pre[v]
        for (int v = 0; v < n; v++) {
            int d = (v == 0) ? pre[0] : (pre[v] ^ pre[up[0][v]]);
            bitUpdate(tin[v], d);
            bitUpdate(tout[v] + 1, d);
        }

        List<Boolean> result = new ArrayList<>();
        char[] cur = s.toCharArray();

        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int x = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                if (cur[x] == c) continue;
                int newMask = 1 << (c - 'a');
                int delta = mask[x] ^ newMask;
                mask[x] = newMask;
                cur[x] = c;
                bitUpdate(tin[x], delta);
                bitUpdate(tout[x] + 1, delta);
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                // LCA via binary lifting
                int a = u, b = v;
                if (depth[a] < depth[b]) { int t = a; a = b; b = t; }
                int diff = depth[a] - depth[b];
                for (int j = 0; j <= LOG; j++) {
                    if (((diff >> j) & 1) != 0) a = up[j][a];
                }
                int lca;
                if (a == b) {
                    lca = a;
                } else {
                    for (int j = LOG; j >= 0; j--) {
                        if (up[j][a] != up[j][b]) {
                            a = up[j][a];
                            b = up[j][b];
                        }
                    }
                    lca = up[0][a];
                }

                int pathMask = bitQuery(tin[u]) ^ bitQuery(tin[v]) ^ mask[lca];
                result.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return result;
    }
}