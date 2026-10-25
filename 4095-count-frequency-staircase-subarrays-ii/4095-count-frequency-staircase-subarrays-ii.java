class Solution {
    public int countStaircase(int[] nums) {
        int n = nums.length;
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }

        int[] freq = new int[mx + 1];
        int[] rank = new int[mx + 1];
        int[] order = new int[n + 1];
        long count = 0;

        int[] t = nums;
        for (int i = 0; i < n; i++) {
            int k = 0;
            int bad = 0;

            for (int j = i; j < n; j++) {
                int v = t[j];
                if (freq[v] == 0) {
                    k++;
                    rank[v] = k;
                    order[k - 1] = v;
                    bad++;
                }
                if ((long) k * (k + 1) / 2 > n - i) {
                    freq[v]++;
                    break;
                }
                freq[v]++;
                if (freq[v] > rank[v])
                    break;
                if (freq[v] == rank[v])
                    bad--;
                if (bad == 0 && j - i + 1 == k * (k + 1) / 2) {
                    count++;
                }
            }
            for (int a = 0; a < k; a++) {
                freq[order[a]] = 0;
            }
        }
        return (int) count;
    }
}