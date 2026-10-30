import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
        }

        char[] lab = labels.toCharArray();
        final int INF = Integer.MAX_VALUE;

        // dist[node][run], run in 1..k
        int[][] dist = new int[n][k + 1];
        for (int[] row : dist) Arrays.fill(row, INF);

        // heap entries: {cost, node, run}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0], u = cur[1], run = cur[2];

            if (cost > dist[u][run]) continue; // stale entry

            for (int[] nb : graph[u]) {
                int v = nb[0], w = nb[1];
                int newRun;
                if (lab[v] == lab[u]) {
                    newRun = run + 1;
                    if (newRun > k) continue; // violates constraint
                } else {
                    newRun = 1;
                }
                int newCost = cost + w;
                if (newCost < dist[v][newRun]) {
                    dist[v][newRun] = newCost;
                    pq.offer(new int[]{newCost, v, newRun});
                }
            }
        }

        int ans = INF;
        for (int r = 1; r <= k; r++) {
            ans = Math.min(ans, dist[n - 1][r]);
        }
        return ans == INF ? -1 : ans;
    }
}