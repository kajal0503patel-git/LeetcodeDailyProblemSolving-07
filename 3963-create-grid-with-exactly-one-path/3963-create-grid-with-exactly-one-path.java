class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');

        }
        int row = 0;
        int col = 0;
        grid[row][col] = '.';
        while (row < m - 1 || col < n - 1) {
            if (col < n - 1) {
                col++;
            } else if (row < m - 1) {
                row++;
            }
            grid[row][col] = '.';
        }
        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i]=new String(grid[i]);
        }
        return result;
    }
}