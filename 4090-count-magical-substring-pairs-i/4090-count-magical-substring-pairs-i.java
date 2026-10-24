class Solution {
    public int countMagicalPairs(String s) {
        int n = s.length();
        long ans = 0;

        String str = s;

        for (int j = 0; j < n - 1; j++) {
            long[] leftCount = new long[27];
            long[] rightCount = new long[27];

            boolean[] seen = new boolean[26];
            int distinct = 0;
            for (int i = j; i >= 0; i--) {
                int c = str.charAt(i) - 'a';
                if (!seen[c]) {
                    seen[c] = true;
                    distinct++;
                }
                leftCount[distinct]++;
            }

            seen = new boolean[26];
            distinct = 0;

            for (int k = j + 1; k < n; k++) {
                int c = str.charAt(k) - 'a';
                if (!seen[c]) {
                    seen[c] = true;
                    distinct++;
                }
                rightCount[distinct]++;
            }
            for (int d = 1; d <= 26; d++) {
                ans += leftCount[d] * rightCount[d];
            }
        }
        return (int) ans;
    }
}