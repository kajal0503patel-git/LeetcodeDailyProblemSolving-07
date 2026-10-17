import java.util.*;

class Solution {
    private int digitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;

        // Har element ke liye {digitSum, value, originalIndex}
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            arr[i][0] = digitSum(nums[i]);
            arr[i][1] = nums[i];
            arr[i][2] = i;
        }

        // Digit sum se sort, tie hone par chhota number pehle
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // arr[j][2] = original index jo sorted position j par jaana chahiye
        // Cycles count karo
        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i] || arr[i][2] == i) {
                continue;
            }

            int cycleSize = 0;
            int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = arr[j][2];
                cycleSize++;
            }

            if (cycleSize > 1) {
                swaps += cycleSize - 1;
            }
        }

        return swaps;
    }
}