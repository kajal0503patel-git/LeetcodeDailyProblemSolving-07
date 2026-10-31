class Solution {
    public int uniquePaths(int[][] grid) {
        final int MOD = 1_000_000_007;
        int m = grid.length;
        int n = grid[0].length;

        long[][] R = new long[m + 1][n + 1];
        long[][] D = new long[m + 1][n + 1];

        R[0][1] = 1;
        D[1][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                long r = R[i][j] % MOD;
                long d = D[i][j] % MOD;

                if (i == m - 1 && j == n - 1) {
                    return (int) ((r + d) % MOD);
                }

                if (grid[i][j] == 0) {
                    long w = (r + d) % MOD;
                    if (j + 1 < n)
                        R[i][j + 1] = (R[i][j + 1] + w) % MOD;
                    if (i + 1 < m)
                        D[i + 1][j] = (D[i + 1][j] + w) % MOD;
                } else {
                    // came moving right -> reflected down
                    if (i + 1 < m)
                        D[i + 1][j] = (D[i + 1][j] + r) % MOD;
                    // came moving down -> reflected right
                    if (j + 1 < n)
                        R[i][j + 1] = (R[i][j + 1] + d) % MOD;
                }
            }
        }
        return 0;
    }
}