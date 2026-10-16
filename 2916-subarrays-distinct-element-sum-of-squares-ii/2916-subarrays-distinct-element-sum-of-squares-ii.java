import java.util.*;

class Solution {
    static final long MOD = 1_000_000_007L;
    int n;
    long[] b1, b2;

    void upd(long[] b, int i, long v) {
        for (; i <= n; i += i & -i) b[i] += v;
    }

    long qry(long[] b, int i) {
        long s = 0;
        for (; i > 0; i -= i & -i) s += b[i];
        return s;
    }

    // range add v on [l, r]
    void rangeAdd(int l, int r, long v) {
        upd(b1, l, v);
        upd(b1, r + 1, -v);
        upd(b2, l, v * (l - 1));
        upd(b2, r + 1, -v * r);
    }

    // prefix sum of f[1..i]
    long prefix(int i) {
        return qry(b1, i) * i - qry(b2, i);
    }

    long rangeSum(int l, int r) {
        return prefix(r) - prefix(l - 1);
    }

    public int sumCounts(int[] nums) {
        n = nums.length;
        b1 = new long[n + 2];
        b2 = new long[n + 2];

        HashMap<Integer, Integer> last = new HashMap<>(); // value -> last index (1-based)

        long S2 = 0;   // sum of f[i]^2 for current j
        long ans = 0;

        for (int j = 1; j <= n; j++) {
            int x = nums[j - 1];
            int p = last.getOrDefault(x, 0);
            int l = p+1 , r = j;

            long sum = rangeSum(l, r);           // exact value, max ~1e10, fits in long
            S2 = (S2 + 2* (sum % MOD) + (r - l+1)) % MOD;

            rangeAdd(l, r, 1);

            ans = (ans + S2) % MOD;
            last.put(x, j);
        }
        return (int) ans;
    }
}