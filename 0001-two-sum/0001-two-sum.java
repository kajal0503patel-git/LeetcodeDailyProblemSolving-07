class Solution {
    public int[] twoSum(int[] nums, int target) {
        int num = nums.length;
        for (int i = 0; i < num; i++) {
            for (int j = i + 1; j < num; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] {};
    }
}