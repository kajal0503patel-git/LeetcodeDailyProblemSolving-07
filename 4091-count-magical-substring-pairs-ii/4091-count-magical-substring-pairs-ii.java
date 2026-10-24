class Solution {
    public long countMagicalPairs(String s) {
        int n = s.length();
        long ans = 0;

        String str = s;

        int[] next = new int[(n + 1) * 26];
        Arrays.fill(next, n * 26, (n + 1) * 26, n);
        for (int i = n - 1; i >= 0; i--) {
            System.arraycopy(next, (i + 1) * 26, next, i * 26, 26);
            next[i * 26 + (str.charAt(i) - 'a')] = i;
        }

        int[] last = new int[26];
        Arrays.fill(last, -1);
        int[] a = new int[26];
        int[] b = new int[26];

        for (int j = 0; j < n - 1; j++) {
            last[str.charAt(j) - 'a'] = j;

            int m = 0;
            for (int c = 0; c < 26; c++) {
                if (last[c] >= 0) {
                    a[m++] = last[c];
                }
                Arrays.sort(a, 0, m);
            }
            int m1 = 0;
            int base = (j + 1) * 26;
            for (int c = 0; c < 26; c++) {
                if (next[base + c] < n) {
                    b[m1++] = next[base + c];
                }
                Arrays.sort(b, 0, m1);
            }
            int limit = Math.min(m, m1);
            for (int d = 1; d <= limit; d++) {
                long left = a[m - d] - (d < m ? a[m - d - 1] : -1);
                long right = (d < m1 ? b[d] : n) - b[d - 1];
                ans += left * right;
            }

        }
        return ans;

    }
}