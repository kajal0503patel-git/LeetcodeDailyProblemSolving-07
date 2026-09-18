class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);

        // Step 1: find first and last occurrence of each character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1)
                first[c] = i;
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();
        for (int c = 0; c < 26; c++) {
            if (first[c] == -1)
                continue;

            int start = first[c];
            int end = last[c];
            boolean valid = true;

            int i = start;
            while (i <= end) {
                int ch = s.charAt(i) - 'a';
                if (first[ch] < start) {
                    valid = false;
                    break;
                }
                end = Math.max(end, last[ch]); // extend right boundary if needed
                i++;
            }

            if (valid) {
                intervals.add(new int[] { start, end });
            }
        }

        intervals.sort((a, b) -> a[0] - b[0]);

        List<String> result = new ArrayList<>();
        int candStart = -1, candEnd = -1;

        for (int[] interval : intervals) {
            if (candStart == -1) {
                candStart = interval[0];
                candEnd = interval[1];
            } else if (interval[0] > candEnd) {
                // disjoint from current candidate -> finalize it
                result.add(s.substring(candStart, candEnd + 1));
                candStart = interval[0];
                candEnd = interval[1];
            } else {
                candStart = interval[0];
                candEnd = interval[1];
            }
        }
        if (candStart != -1) {
            result.add(s.substring(candStart, candEnd + 1));
        }

        return result;
    }
}