class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;

        int even = 1; // empty prefix
        int odd = 0;
        int parity = 0;
        long res = 0;

        for (int x : arr) {
            parity = (parity + x) & 1;

            if (parity == 1) {
                res += even;
                odd++;
            } else {
                res += odd;
                even++;
            }
            res %= MOD;
        }
        return (int) res;
    }
}