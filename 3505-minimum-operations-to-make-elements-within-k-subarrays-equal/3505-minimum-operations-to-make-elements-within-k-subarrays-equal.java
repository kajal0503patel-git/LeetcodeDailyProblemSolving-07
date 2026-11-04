class Solution {
    static class MedianWindow {
        TreeMap<Long, Integer> low = new TreeMap<>();
        TreeMap<Long, Integer> high = new TreeMap<>();
        int lowSize = 0;
        int highSize = 0;
        long lowSum = 0;
        long highSum = 0;

        void add(TreeMap<Long, Integer> m, long v) {
            m.merge(v, 1, Integer::sum);
        }

        void remove(TreeMap<Long, Integer> m, long v) {
            int c = m.get(v);
            if (c == 1)
                m.remove(v);
            else
                m.put(v, c - 1);
        }

        void insert(long v) {
            if (lowSize == 0 || v <= low.lastKey()) {
                add(low, v);
                lowSize++;
                lowSum += v;
            } else {
                add(high, v);
                highSize++;
                highSum += v;
            }
            rebalance();
        }

        void erase(long v) {
            if (low.containsKey(v)) {
                remove(low, v);
                lowSize--;
                lowSum -= v;
            } else {
                remove(high, v);
                highSize--;
                highSum -= v;
            }
            rebalance();
        }

        // low mein size = high size ya high size + 1 rakhte hain
        void rebalance() {
            while (lowSize > highSize + 1) {
                long v = low.lastKey();
                remove(low, v);
                lowSize--;
                lowSum -= v;
                add(high, v);
                highSize++;
                highSum += v;
            }
            while (lowSize < highSize) {
                long v = high.firstKey();
                remove(high, v);
                highSize--;
                highSum -= v;
                add(low, v);
                lowSize++;
                lowSum += v;
            }
        }

        long cost() {
            long med = low.lastKey();
            return med * lowSize - lowSum + highSum - med * highSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        // Step 1: har window ka cost
        long[] cost = new long[n - x + 1];
        MedianWindow mw = new MedianWindow();
        for (int i = 0; i < n; i++) {
            mw.insert(nums[i]);
            if (i >= x)
                mw.erase(nums[i - x]);
            if (i >= x - 1)
                cost[i - x + 1] = mw.cost();
        }

        // Step 2: DP
        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[n + 1][k + 1];
        for (long[] row : dp)
            Arrays.fill(row, INF);
        for (int i = 0; i <= n; i++)
            dp[i][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i - 1][j]; // skip
                if (i >= x && dp[i - x][j - 1] < INF) { // window lo
                    dp[i][j] = Math.min(dp[i][j], dp[i - x][j - 1] + cost[i - x]);
                }
            }
        }
        return dp[n][k];
    }
}
