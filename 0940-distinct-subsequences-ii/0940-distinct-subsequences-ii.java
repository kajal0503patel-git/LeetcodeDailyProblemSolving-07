class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;
        long[] dp = new long[26];
        long total = 0;

        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';
            long f = (total + 1) % MOD;                  
            long newTotal = (total - dp[idx] + f) % MOD;  
            newTotal = (newTotal + MOD) % MOD;            
            dp[idx] = f;
            total = newTotal;

        }
        return (int) total;
    }
}