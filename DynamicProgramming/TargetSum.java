public class Solution{
  public static int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num: nums) {
            sum+= num;
        }
        if(((sum - target) % 2 == 1) || (target > sum))
            return 0;
        int diff = (sum - target)/2;
        return countNoOfSubset(nums, nums.length, diff);
    }

  public static int countNoOfSubset(int[] nums, int size, int sum) {
        int[][] integerSubsetMatrix = new int[size + 1][sum + 1];
        integerSubsetMatrix[0][0] = 1;
        for (int i = 1; i < size + 1; i++) {
            for (int j = 0; j < sum + 1; j++) {
                if (nums[i-1] <= j)
                    integerSubsetMatrix[i][j] = integerSubsetMatrix[i-1][j-nums[i-1]] + integerSubsetMatrix[i-1][j];
                else
                    integerSubsetMatrix[i][j] = integerSubsetMatrix[i-1][j];
            }
        }
        return integerSubsetMatrix[size][sum];
    }

}
