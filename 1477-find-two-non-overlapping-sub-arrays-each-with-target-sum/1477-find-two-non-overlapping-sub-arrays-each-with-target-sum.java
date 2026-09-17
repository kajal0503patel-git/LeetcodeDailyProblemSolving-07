class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = Integer.MAX_VALUE / 2;

        int[] dp = new int[n];
        int ans = INF;
        int left = 0;
        int currSum = 0;

        for (int right = 0; right < n; right++) {
            currSum += arr[right];
            while (currSum > target) {
                currSum -= arr[left];
                left++;
            }
            if (currSum == target) {
                int currLen = right - left + 1;
                if (left > 0 && dp[left - 1] != INF) {
                    ans = Math.min(ans, currLen + dp[left - 1]);

                }
                if (right > 0) {
                    dp[right] = Math.min(dp[right - 1], currLen);
                } else {
                    dp[right] = currLen;
                }
            } else {
                if (right > 0) {
                    dp[right] = dp[right - 1];
                } else {
                    dp[right] = INF;
                }
            }
        }
        return ans == INF ? -1 : ans;

    }
}