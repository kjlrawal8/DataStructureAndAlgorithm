class Solution {
    int[][] dp ;
    public int superEggDrop(int k, int n) {
        dp = new int[k+1][n+1];
         for(int[] a: dp)
            Arrays.fill(a, -1);
        return memoizedWithBinaryEggDrop(k,n);
    }

    public int memoizedWithBinaryEggDrop(int k, int n) {
        if(k == 1)
            return n;
        if(n == 0 || n == 1)
            return n;
        if (dp[k][n] != -1) {
            return dp[k][n];
        }
        int ans = Integer.MAX_VALUE;

        int left = 1, right = n;
        while(left <= right) {
            int i = left + (right - left) / 2;
            int low = memoizedWithBinaryEggDrop(k-1, i-1);
            int high = memoizedWithBinaryEggDrop(k, n-i);

            int temp = 1 + Math.max(low, high);

            ans = Math.min(temp, ans);

            if(low < high) 
                left = i + 1;
            else 
                right = i - 1;
        }
        return dp[k][n] = ans;
    }

  public static int memoizedEggDrop(int k, int n) {
        if(k == 1)
            return n;
        if(n == 0 || n == 1)
            return n;
        if (dp[k][n] != -1) {
            return dp[k][n];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int temp = Math.max(superEggDrop(k-1, i-1), superEggDrop(k, n-i));
            ans = Math.min(temp, ans);
        }
        return dp[k][n] = ans + 1;
    }

  public static int recursiveSuperEggDrop(int k, int n) {
        if(k == 1)
            return n;
        if(n == 0 || n == 1)
            return n;
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int temp = Math.max(recursiveSuperEggDrop(k-1, i-1), recursiveSuperEggDrop(k, n-i));
            ans = Math.min(temp, ans);
        }
        return ans + 1;
    }
}
