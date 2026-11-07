class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        PriorityQueue<int[]> free = new PriorityQueue<>(
                (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));

        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]);
            if (a[1] != b[1])
                return Integer.compare(a[1], b[1]);
            return Integer.compare(a[2], b[2]);
        });

        for (int i = 0; i < n; i++) {
            free.offer(new int[] { servers[i], i });
        }

        long time = 0;
        for (int j = 0; j < m; j++) {
            time = Math.max(time, j);

            if (free.isEmpty()) {
                time = Math.max(time, busy.peek()[0]);
            }
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] s = busy.poll();
                free.offer(new int[] { s[1], s[2] });
            }

            int[] best = free.poll();
            ans[j] = best[1];
            busy.offer(new int[] { (int) (time + tasks[j]), best[0], best[1] });

        }
        return ans;
    }
}