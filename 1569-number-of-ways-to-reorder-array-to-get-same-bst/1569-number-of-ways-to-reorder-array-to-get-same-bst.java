import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;

        // Pascal's triangle for C(n, k) mod p
        comb = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + (j <= i - 1 ? comb[i - 1][j] : 0)) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int x : nums) list.add(x);

        // subtract 1 to exclude the original ordering
        return (int) ((count(list) - 1 + MOD) % MOD);
    }

    private long count(List<Integer> arr) {
        int n = arr.size();
        if (n <= 2) return 1;

        int root = arr.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            int v = arr.get(i);
            if (v < root) left.add(v);
            else right.add(v);
        }

        long ways = comb[n - 1][left.size()];
        ways = ways * count(left) % MOD;
        ways = ways * count(right) % MOD;
        return ways;
    }
}