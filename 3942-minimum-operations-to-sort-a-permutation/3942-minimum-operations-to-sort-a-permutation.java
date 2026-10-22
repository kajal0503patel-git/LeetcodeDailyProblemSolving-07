class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int best = Integer.MAX_VALUE;

        int s = nums[0];
        boolean inc = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != (i + s) % n) {
                inc = false;
                break;
            }
        }
        if (inc) {
            int t = (n - s) % n;
            if (t == 0)
                return 0;
            best = Math.min(best, Math.min(t, s + 2));
        }
        boolean dec = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != ((s - i) % n + n) % n) {
                dec = false;
                break;
            }
        }
        if (dec) {
            int a = (s + 1) % n; // rotate first, then reverse
            int b = (n - a) % n; // reverse first, then rotate
            best = Math.min(best, 1 + Math.min(a, b));
        }

        return best == Integer.MAX_VALUE ? -1 : best;
    }
}