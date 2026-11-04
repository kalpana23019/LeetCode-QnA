
class Solution {

    static class WindowMedian {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        int leftSize = 0, rightSize = 0;
        long leftSum = 0, rightSum = 0;

        void addTo(TreeMap<Integer, Integer> map, int value) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }

        void removeFrom(TreeMap<Integer, Integer> map, int value) {
            int count = map.get(value);
            if (count == 1) {
                map.remove(value);
            } else {
                map.put(value, count - 1);
            }
        }

        void add(int value) {
            if (leftSize == 0 || value <= left.lastKey()) {
                addTo(left, value);
                leftSize++;
                leftSum += value;
            } else {
                addTo(right, value);
                rightSize++;
                rightSum += value;
            }

            balance();
        }

        void remove(int value) {
            if (left.containsKey(value)) {
                removeFrom(left, value);
                leftSize--;
                leftSum -= value;
            } else {
                removeFrom(right, value);
                rightSize--;
                rightSum -= value;
            }

            balance();
        }

        void balance() {
            while (leftSize > rightSize + 1) {
                int value = left.lastKey();

                removeFrom(left, value);
                leftSize--;
                leftSum -= value;

                addTo(right, value);
                rightSize++;
                rightSum += value;
            }

            while (leftSize < rightSize) {
                int value = right.firstKey();

                removeFrom(right, value);
                rightSize--;
                rightSum -= value;

                addTo(left, value);
                leftSize++;
                leftSum += value;
            }
        }

        long getCost() {
            int median = left.lastKey();

            long leftCost =
                    (long) median * leftSize - leftSum;

            long rightCost =
                    rightSum - (long) median * rightSize;

            return leftCost + rightCost;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int windows = n - x + 1;

        long[] cost = new long[windows];

        WindowMedian median = new WindowMedian();

        // Calculate the cost of each window of size x.
        for (int i = 0; i < n; i++) {
            median.add(nums[i]);

            if (i >= x) {
                median.remove(nums[i - x]);
            }

            if (i >= x - 1) {
                cost[i - x + 1] = median.getCost();
            }
        }

        // dp[i][j] = minimum cost using first i positions
        // to select j non-overlapping windows.
        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {

                // Option 1: skip current position.
                dp[i][j] = dp[i - 1][j];

                // Option 2: select a window ending at i - 1.
                if (i >= x && dp[i - x][j - 1] != INF) {
                    dp[i][j] = Math.min(
                            dp[i][j],
                            dp[i - x][j - 1] + cost[i - x]
                    );
                }
            }
        }

        return dp[n][k];
    }
}