import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = nums[n - 1 - i];

        // Window [l..r] on reversed array.
        // cost = sum_{i=l..r} (max(a[i..r]) - a[i])
        // Monotonic deque of indices with strictly decreasing values from front to back.
        // Each deque element j "owns" positions (prev_j, j] inside the window,
        // where prev_j is the previous deque index (or l-1), all with max = a[j].
        Deque<Integer> dq = new ArrayDeque<>();
        long sumMax = 0;   // sum of max(a[i..r]) for i in [l..r]
        long sumVal = 0;   // sum of a[i] for i in [l..r]
        long ans = 0;
        int l = 0;

        for (int r = 0; r < n; r++) {
            // add a[r]
            while (!dq.isEmpty() && a[dq.peekLast()] <= a[r]) {
                int j = dq.pollLast();
                int prev = dq.isEmpty() ? l - 1 : dq.peekLast();
                sumMax -= (long) a[j] * (j - prev);
            }
            int prev = dq.isEmpty() ? l - 1 : dq.peekLast();
            sumMax += (long) a[r] * (r - prev);
            dq.addLast(r);
            sumVal += a[r];

            // shrink from left while cost > k
            while (sumMax - sumVal > k) {
                int front = dq.peekFirst();
                // position l owned by front; its max is a[front]
                sumMax -= a[front];
                sumVal -= a[l];
                if (front == l) dq.pollFirst();
                l++;
            }
            ans += r - l + 1;
        }
        return ans;
    }
}