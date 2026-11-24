class Solution {
    public int divide(int dividend, int divisor) {
        // Only overflow case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Result is negative if exactly one number is negative
        boolean negative = (dividend < 0) != (divisor < 0);

        // Use long so abs() doesn't overflow for MIN_VALUE
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        long result = 0;

        while (a >= b) {
            long temp = b;
            long multiple = 1;

            // Double temp while it still fits into a
            while (a >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }

            a -= temp;           // remove the chunk we just found
            result += multiple;  // add how many times divisor fit
        }

        return negative ? (int) -result : (int) result;
    }
}