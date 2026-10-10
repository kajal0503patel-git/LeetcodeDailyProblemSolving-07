class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;

        int maxDiff = 0;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        long[] cnt = new long[maxDiff + 1];
        for (int d : diff) {
            cnt[d]++;
        }

        for (int v = maxDiff; v > 0 && k > 0; v--) {
            long c = cnt[v];
            if (c == 0) {
                continue;
            }
            if (k >= c) {
                cnt[v - 1] += c;
                cnt[v] = 0;
                k -= c;
            } else {
                cnt[v - 1] += k;
                cnt[v] -= k;
                k = 0;
            }
        }
        long result = 0;
        for (int v = 1; v <= maxDiff; v++) {
            result += cnt[v] * (long) v * v;
        }
        return result;
    }
}