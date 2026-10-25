class Solution {
    public int countStaircase(int[] nums) {
        int n = nums.length;
        int[] freq = new int[100001];
        int[] order = new int[n + 1];
        int count = 0;

        int[] a = nums;

        for (int i = 0; i < n; i++) {
            int k = 0;
            for (int j = i; j < n; j++) {
                int v = a[j];
                if (freq[v] == 0) {
                    order[k++] = v;
                }
                freq[v]++;

                int len = j - i + 1;
                if (len == k * (k + 1) / 2) {
                    boolean ok = true;
                    for (int t = 0; t < k; t++) {
                        if (freq[order[t]] != t + 1) {
                            ok = false;
                            break;
                        }
                    }
                    if (ok) {
                        count++;
                    }
                }
            }
            for (int t = 0; t < k; t++) {
                freq[order[t]] = 0;
            }
        }
        return count;
    }
}