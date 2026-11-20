import java.util.Arrays;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        int maxVal = nums[nums.length - 1] + 2;
        int[] dp = new int[maxVal + 1];
        int ans = 1;

        for (int x : nums) {
            // Use x + 1 first (reads old dp[x]), so the element isn't counted twice
            dp[x + 1] = dp[x] + 1;
            // Use x (reads dp[x - 1])
            dp[x] = (x > 0 ? dp[x - 1] : 0) + 1;

            ans = Math.max(ans, Math.max(dp[x], dp[x + 1]));
        }
        return ans;
    }
}