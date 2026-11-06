import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        // Sort logs by time
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        // Sort query indices by query value
        int q = queries.length;
        Integer[] order = new Integer[q];
        for (int i = 0; i < q; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> Integer.compare(queries[a], queries[b]));

        int[] count = new int[n + 1];
        int active = 0;          // servers with at least one request in the window
        int left = 0, right = 0; // window over logs: [left, right)
        int[] ans = new int[q];

        for (int idx : order) {
            int end = queries[idx];
            int start = end - x;

            // Add logs with time <= end
            while (right < logs.length && logs[right][1] <= end) {
                if (count[logs[right][0]]++ == 0) active++;
                right++;
            }

            // Remove logs with time < start
            while (left < right && logs[left][1] < start) {
                if (--count[logs[left][0]] == 0) active--;
                left++;
            }

            ans[idx] = n - active;
        }

        return ans;
    }
}