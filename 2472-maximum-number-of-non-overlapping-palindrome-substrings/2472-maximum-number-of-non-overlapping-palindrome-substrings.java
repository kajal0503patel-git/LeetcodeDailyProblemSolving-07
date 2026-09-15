class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        if (n < k)
            return 0;

        // pal[i][j] = true if s[i..j] (inclusive) is a palindrome
        boolean[][] pal = new boolean[n][n];
        for (int i = 0; i < n; i++)
            pal[i][i] = true;

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (len == 2 || pal[i + 1][j - 1]) {
                        pal[i][j] = true;
                    }
                }
            }
        }
        int[] dp = new int[n + 1];
        // dp[0] = 0 by default

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1]; // don't use s[i-1] as end of a chosen substring

            // try the two minimal lengths: k and k+1
            for (int len = k; len <= k + 1; len++) {
                int start = i - len;
                if (start >= 0) {
                    int end = i - 1;
                    if (pal[start][end]) {
                        dp[i] = Math.max(dp[i], dp[start] + 1);
                    }
                }
            }
        }

        return dp[n];
    }
}