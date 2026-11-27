class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        // g[i] = number of free copies you get from buying item i
        int[] g = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) g[i]++;
            }
        }

        // Sort indices by price, highest first
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> items[b][1] - items[a][1]);

        // dp[c] = best value using items processed so far, spending at most c
        int[] dp = new int[budget + 1];
        int ans = 0;

        for (int idx : order) {
            int p = items[idx][1];
            int gain = 1 + g[idx];

            // Treat this item as the CHEAPEST item bought.
            // Leftover money is spent on extra copies of it.
            for (int c = 0; c + p <= budget; c++) {
                int extra = (budget - c - p) / p;
                ans = Math.max(ans, dp[c] + gain + extra);
            }

            // Now add this item to the 0/1 knapsack (buy it once)
            for (int c = budget; c >= p; c--) {
                dp[c] = Math.max(dp[c], dp[c - p] + gain);
            }
        }

        return ans;
    }
}