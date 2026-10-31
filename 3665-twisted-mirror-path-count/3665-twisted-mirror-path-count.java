class Solution {
    private static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // dp[i][j][0]: ways arriving from the left
        // dp[i][j][1]: ways arriving from above
        int[][][] dp = new int[m][n][2];

        // Start at (0, 0). It is an empty cell.
        dp[0][0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int fromLeft = dp[i][j][0];
                int fromAbove = dp[i][j][1];

                if (grid[i][j] == 0) {
                    // Empty cell: continue right
                    if (j + 1 < n) {
                        dp[i][j + 1][0] =
                            (int) ((dp[i][j + 1][0] + (long) fromLeft + fromAbove) % MOD);
                    }

                    // Empty cell: continue down
                    if (i + 1 < m) {
                        dp[i + 1][j][1] =
                            (int) ((dp[i + 1][j][1] + (long) fromLeft + fromAbove) % MOD);
                    }

                } else {
                    // Mirror: paths arriving from left reflect down
                    if (i + 1 < m) {
                        dp[i + 1][j][1] =
                            (int) ((dp[i + 1][j][1] + (long) fromLeft) % MOD);
                    }

                    // Mirror: paths arriving from above reflect right
                    if (j + 1 < n) {
                        dp[i][j + 1][0] =
                            (int) ((dp[i][j + 1][0] + (long) fromAbove) % MOD);
                    }
                }
            }
        }

        return (int) (
            ((long) dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD
        );
    }
}