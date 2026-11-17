import java.util.Arrays;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        
        // cost[i][j] stores the min changes to make s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int[] row : cost) {
            Arrays.fill(row, Integer.MAX_VALUE / 2);
        }

        // Step 1: Precompute costs for all substrings of length >= 2
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = getMinChanges(arr, i, j);
            }
        }

        // Step 2: DP to partition prefix of length i into p semi-palindromes
        // dp[i][p] = min changes for s[0..i-1] split into p parts
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE / 2);
        }
        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            // Each partition needs at least length 2, so i starts at 2 * p
            for (int i = 2 * p; i <= n; i++) {
                // Previous prefix must have length at least 2 * (p - 1)
                for (int m = 2 * (p - 1); m <= i - 2; m++) {
                    dp[i][p] = Math.min(dp[i][p], dp[m][p - 1] + cost[m][i - 1]);
                }
            }
        }

        return dp[n][k];
    }

    // Helper method to find min changes to make arr[start..end] a semi-palindrome
    private int getMinChanges(char[] arr, int start, int end) {
        int len = end - start + 1;
        int minChanges = Integer.MAX_VALUE;

        // Try all proper divisors d of len
        for (int d = 1; d < len; d++) {
            if (len % d != 0) continue;

            int changes = 0;
            // Check each of the d interleaved groups
            for (int offset = 0; offset < d; offset++) {
                int left = start + offset;
                int right = end - d + 1 + offset;
                
                // Standard two-pointer palindrome check with step size d
                while (left < right) {
                    if (arr[left] != arr[right]) {
                        changes++;
                    }
                    left += d;
                    right -= d;
                }
            }
            minChanges = Math.min(minChanges, changes);
        }

        return minChanges;
    }
}