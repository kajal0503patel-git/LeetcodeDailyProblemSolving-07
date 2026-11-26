class Solution {
    public double myPow(double x, int n) {
        long N = n; // avoid overflow when n = Integer.MIN_VALUE
        boolean negative = N < 0;
        if (negative) N = -N;

        double result = 1.0;
        double base = x;

        while (N > 0) {
            if ((N & 1) == 1) {
                result *= base;
            }
            base *= base;
            N >>= 1;
        }

        return negative ? 1.0 / result : result;
    }
}