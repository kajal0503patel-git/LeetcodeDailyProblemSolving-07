class Solution {

    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0)
                continue; // k must divide n
            if (isSortable(nums, k))
                ans += k;
        }
        return ans;
    }

    private boolean isSortable(int[] nums, int k) {
        int n = nums.length;
        int prevMax = Integer.MIN_VALUE; // max of previous block

        for (int s = 0; s < n; s += k) {
            int e = s + k - 1;
            int min = nums[s], max = nums[s];
            int drops = 0;

            for (int i = s; i <= e; i++) {
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i]);
                if (i < e && nums[i] > nums[i + 1])
                    drops++; // drop inside block
            }
            if (nums[e] > nums[s])
                drops++; // wrap-around drop (circular)

            if (drops > 1)
                return false; // can't be a rotated sorted block
            if (prevMax > min)
                return false; // blocks overlap in value range

            prevMax = max;
        }
        return true;
    }
}