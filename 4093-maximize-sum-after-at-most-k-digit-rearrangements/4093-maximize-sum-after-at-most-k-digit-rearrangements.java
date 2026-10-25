class Solution {
    public long maximizeSum(int[] nums, int k) {
        int n = nums.length;
        long[] gains = new long[n];
        long sum = 0;

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            sum += num;

            int[] count = new int[10];
            int t = num;
            while (t > 0) {
                count[t % 10]++;
                t /= 10;
            }
            long best = 0;
            for (int d = 9; d >= 0; d--) {
                for (int c = 0; c < count[d]; c++) {
                    best = best * 10 + d;
                }
            }
            gains[i] = best - num;
        }

        int[] b = nums;

        Arrays.sort(gains);

        for (int i = 0; i < k && i < n; i++) {
            sum += gains[n - 1 - i];
        }
        return sum;
    }
}