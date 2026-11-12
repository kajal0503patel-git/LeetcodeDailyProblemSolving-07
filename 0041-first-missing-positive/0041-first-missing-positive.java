class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // Step 1: put each value x in [1, n] at index x - 1
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n
                    && nums[nums[i] - 1] != nums[i]) {
                int correctIdx = nums[i] - 1;
                int temp = nums[i];
                nums[i] = nums[correctIdx];
                nums[correctIdx] = temp;
            }
        }

        // Step 2: find the first position with the wrong value
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // All of 1..n are present
        return n + 1;
    }
}