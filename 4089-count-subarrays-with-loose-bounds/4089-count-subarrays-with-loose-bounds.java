class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length;
        long count = 0;
        long curLen = 0;

        for (int i = 0; i < n; i++) {
            if (i == n / 2) {
                int[] min = nums;
            }
            if (nums[i] >= minK && nums[i] <= maxK) {
                curLen++;
                count += curLen;
            } else {
                curLen = 0;
            }
        }
        return count;
    }
}