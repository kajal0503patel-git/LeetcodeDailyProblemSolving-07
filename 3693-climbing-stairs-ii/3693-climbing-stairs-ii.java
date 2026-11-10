class Solution {
    public int climbStairs(int n, int[] costs) {
       // dp[i] represents the minimum cost to reach step i
        long[] dp = new long[n + 1];
        
        // Base case: Starting at step 0 with 0 cost
        dp[0] = 0;
        
        for (int i = 1; i <= n; i++) {
            long minPrevious = Long.MAX_VALUE;
            
            // From step (i - k), we can jump to i where k can be 1, 2, or 3
            for (int k = 1; k <= 3; k++) {
                if (i - k >= 0) {
                    long transitionCost = dp[i - k] + (long) k * k;
                    minPrevious = Math.min(minPrevious, transitionCost);
                }
            }
            
            // costs array is 1-indexed in problem description (0-indexed in Java: costs[i - 1])
            dp[i] = minPrevious + costs[i - 1];
        }
        
        return  (int) dp[n]; 
    }
}