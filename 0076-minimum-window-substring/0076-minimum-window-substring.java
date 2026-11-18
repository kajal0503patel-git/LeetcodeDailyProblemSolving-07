
class Solution {
    public String minWindow(String s, String t) {

        int m = s.length();
        int n = t.length();

        // Frequency array
        int[] need = new int[128];

        for (int i = 0; i < n; i++) {
            need[t.charAt(i)]++;
        }

        int left = 0;
        int matched = 0;

        int start = 0;
        int minLen = Integer.MAX_VALUE;

        // Expand the window
        for (int right = 0; right < m; right++) {

            char c = s.charAt(right);

            // Check if character is required
            if (need[c] > 0) {
                matched++;
            }

            need[c]--;

            // Window contains all required characters
            while (matched == n) {

                // Update minimum window
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                // Shrink the window
                char ch = s.charAt(left);

                need[ch]++;

                if (need[ch] > 0) {
                    matched--;
                }

                left++;
            }
        }

        // No valid window found
        if (minLen == Integer.MAX_VALUE) {
            return "";
        }

        return s.substring(start, start + minLen);
    }
}
