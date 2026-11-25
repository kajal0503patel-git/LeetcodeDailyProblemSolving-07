class Solution {
    static final long M1 = 1_000_000_007L, M2 = 998_244_353L;
    static final long B1 = 911_382_323L, B2 = 972_663_749L;

    long[] h1, h2, p1, p2;
    int[] nums;
    int n;

    public int smallestUniqueSubarray(int[] nums) {
        this.nums = nums;
        this.n = nums.length;

        h1 = new long[n + 1];
        h2 = new long[n + 1];
        p1 = new long[n + 1];
        p2 = new long[n + 1];
        p1[0] = p2[0] = 1;

        // Build prefix hashes and powers
        for (int i = 0; i < n; i++) {
            h1[i + 1] = (h1[i] * B1 + nums[i]) % M1;
            h2[i + 1] = (h2[i] * B2 + nums[i]) % M2;
            p1[i + 1] = (p1[i] * B1) % M1;
            p2[i + 1] = (p2[i] * B2) % M2;
        }

        // Binary search on the length
        int lo = 1, hi = n;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (hasUnique(mid))
                hi = mid;
            else
                lo = mid + 1;
        }
        return lo;
    }

    // Is there any subarray of length L that appears exactly once?
    private boolean hasUnique(int L) {
        HashMap<Long, Integer> count = new HashMap<>();
        for (int i = 0; i + L <= n; i++) {
            long a = ((h1[i + L] - h1[i] * p1[L]) % M1 + M1) % M1;
            long b = ((h2[i + L] - h2[i] * p2[L]) % M2 + M2) % M2;
            long key = a * M2 + b;
            count.merge(key, 1, Integer::sum);
        }
        for (int c : count.values()) {
            if (c == 1)
                return true;
        }
        return false;
    }
}