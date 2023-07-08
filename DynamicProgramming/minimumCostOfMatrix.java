public class TryOut {

  public static void main(String[] args) throws Exception {
        int[] w = {1,2,3,4,3};
        System.out.println("MCM = " + maxCoins(w));
    }
  
  public static int maxCoins(int[] nums) {
        int n = nums.length;
        mcmDpMatrix = new int[n+1][n+1];
        Arrays.stream(mcmDpMatrix).forEach(row -> Arrays.fill(row, -1));
        return optimizedMemoizationMCM(nums, 1, n-1);
    }

    private static int optimizedMemoizationMCM(int[] nums, int i, int j) {
        if(i == j)
            return 0;
        if(mcmDpMatrix[i][j] != -1)
            return mcmDpMatrix[i][j];
        mcmDpMatrix[i][j] = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int left,right;
            if(mcmDpMatrix[i][k] != -1)
                left = mcmDpMatrix[i][k];
            else
                left = optimizedMemoizationMCM(nums, i, k);
            if(mcmDpMatrix[k+1][j] != -1)
                right = mcmDpMatrix[k+1][j];
            else
                right = optimizedMemoizationMCM(nums,k+1, j);
            int temp = left + right + (nums[i-1] * nums[k] * nums[j]);
            if(temp < mcmDpMatrix[i][j])
                mcmDpMatrix[i][j] = temp;
        }
        return mcmDpMatrix[i][j];
    }

    /*
        nums = [3,1,5,8]
        Output: 167
        Explanation:
        nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
        coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
    */
    /*
        i = 0, j = n-1
    */

    private static int solveMCM(int[] nums, int i, int j) {
        if(i == j)
            return 0;
        int ans = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int temp = solveMCM(nums, i, k) + solveMCM(nums, k+1, j) + (nums[i-1] * nums[k] * nums[j]);
            if(temp < ans)
                ans = temp;
        }
        return ans;
    }

    private static int memoizationMCM(int[] nums, int i, int j) {
        if(i == j)
            return 0;
        if(mcmDpMatrix[i][j] != -1)
            return mcmDpMatrix[i][j];
        mcmDpMatrix[i][j] = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int temp = memoizationMCM(nums, i, k) + memoizationMCM(nums, k+1, j) + (nums[i-1] * nums[k] * nums[j]);
            if(temp < mcmDpMatrix[i][j])
                mcmDpMatrix[i][j] = temp;
        }
        return mcmDpMatrix[i][j];
    }
