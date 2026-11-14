class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();

        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }

        TreeMap<Integer, Integer> window = new TreeMap<>();
        int ans = Integer.MAX_VALUE;

        for (int e = l; e <= n; e++) {
            window.merge(prefix[e - l], 1, Integer::sum);

            int out = e - r - 1;
            if (out >= 0) {
                int key = prefix[out];
                if (window.get(key) == 1) {
                    window.remove(key);
                } else {
                    window.put(key, window.get(key) - 1);
                }
            }
            Integer best = window.lowerKey(prefix[e]);
            if (best != null) {
                ans = Math.min(ans, prefix[e] - best);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}