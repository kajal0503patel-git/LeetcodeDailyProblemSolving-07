class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = cost1;
        long c2 = cost2;
        long cb = costBoth;
        long n1 = need1;
        long n2 = need2;
        long[] candidates = { 0, Math.min(n1, n2), Math.max(n1, n2) };

        long ans = Long.MAX_VALUE;
        for (long k : candidates) {
            long total = k * cb + Math.max(0, n1 - k) * c1 + Math.max(0, n2 - k) * c2;

            ans = Math.min(ans, total);
        }
        return ans;
    }
}