class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
         int n = items.length;

        // cnt[f] = number of items having factor f
        int[] cnt = new int[n + 1];
        for (int[] it : items) cnt[it[0]]++;

        // mult[f] = number of items whose factor is a multiple of f
        int[] mult = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            for (int k = f; k <= n; k += f) {
                mult[f] += cnt[k];
            }
        }

        // d[i] = number of other items j with factor_i | factor_j
        long[][] arr = new long[n][2]; // {price, d}
        long minPrice = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            arr[i][0] = items[i][1];
            arr[i][1] = mult[items[i][0]] - 1;
            minPrice = Math.min(minPrice, arr[i][0]);
        }

        Arrays.sort(arr, (a, b) -> Long.compare(a[0], b[0]));

        long rem = budget;
        long ans = 0;

        for (int i = 0; i < n; i++) {
            long p = arr[i][0];
            long d = arr[i][1];
            if (p >= 2 * minPrice) break;   // never beneficial
            if (d == 0) continue;
            long x = Math.min(d, rem / p);
            rem -= x * p;
            ans += 2 * x;
        }

        ans += rem / minPrice;
        return (int)ans;
    }
}