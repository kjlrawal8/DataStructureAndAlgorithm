class Solution {
    static int[][] mcmDpMatrix;
    public int maxCoins(int[] nums) {
        int n = nums.length;
        if(n < 1) return 0;
        mcmDpMatrix = new int[n+1][n+1];
        Arrays.stream(mcmDpMatrix).forEach(row -> Arrays.fill(row, -1));
        return solve(nums, 0, n-1);
    }

    private int solve(int[] nums, int i, int j) {
        if(i > j)
            return 0;
        if(mcmDpMatrix[i][j] != -1)
            return mcmDpMatrix[i][j];

        int ans = Integer.MIN_VALUE;
        for (int k = i; k <= j; k++) {
            int left = (i - 1 < 0) ? 1 : nums[i - 1];
            int right = (j + 1 >= nums.length) ? 1 : nums[j + 1];
            int coins = solve(nums, i, k - 1) + solve(nums, k + 1, j) + (left * nums[k] * right);
            ans = Math.max(coins, ans);
        }
        return mcmDpMatrix[i][j] = ans;
    }
}
