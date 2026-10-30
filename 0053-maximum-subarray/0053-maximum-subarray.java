class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int cs = 0;
        int ms = nums[0];
        for (int i = 0; i < n; i++) {
            if (cs < 0) {
                cs = 0;
            }

            cs += nums[i];
            ms = Math.max(cs, ms);
        }
        return ms;
    }
}