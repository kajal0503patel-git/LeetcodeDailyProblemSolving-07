class Solution {
    public int countIslands(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        int[] stack = new int[rows * cols];
        int[] dr = { 1, -1, 0, 0 };
        int[] dc = { 0, 0, 1, -1 };

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] > 0) {
                    long sum = grid[i][j];
                    grid[i][j] = 0;
                    int top = 0;
                    stack[top++] = i * cols + j;

                    while (top > 0) {
                        int cur = stack[--top];
                        int r = cur / cols;
                        int c = cur % cols;
                        for (int d = 0; d < 4; d++) {
                            int nr = r + dr[d];
                            int nc = c + dc[d];
                            if (nr >= 0 && nc >= 0 && nr < rows && nc < cols && grid[nr][nc] > 0) {
                                sum += grid[nr][nc];
                                grid[nr][nc] = 0;
                                stack[top++] = nr * cols + nc;
                            }
                        }
                    }
                    if (sum % k == 0) {
                        count++;
                    }
                }
            }
        }
        return count;

    }

}