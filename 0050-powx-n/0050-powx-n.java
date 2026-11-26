class Solution {
    public double myPow(double x, long n) {

        long e = (long) n;

        if (n == 0)
            return 1;
        else if (x == 0)
            return 0;

        else if (n < 0) {

            double half = myPow(x, -e / 2);

            if (e % 2 == 0) {
                return 1 / (half * half);
            } else {
                return 1 / (x * half * half);
            }
        }

        else {

            double half = myPow(x, e / 2);

            if (e % 2 == 0) {
                return half * half;
            } else {
                return x * half * half;
            }
        }
    }
}