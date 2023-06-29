class Solution {
    static boolean[][] subsetMatrix;
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num:nums) {
            sum += num;
        }
        if(sum % 2 != 0)
            return false;
        subsetMatrix = new boolean[nums.length+1][(sum/2) + 1];
        return isSubset(nums, sum/2);
    }
    public boolean isSubset(int[] nums, int sum) {
        for (int i = 0; i <= nums.length; i++) {
            for (int j = 0; j <= sum ; j++) {
                if(i == 0)
                    subsetMatrix[i][j] = false;
                else if(j == 0)
                    subsetMatrix[i][j] = true;
                else if (nums[i-1] <= j)
                    subsetMatrix[i][j] = subsetMatrix[i-1][j-nums[i-1]] || subsetMatrix[i-1][j];
                else
                    subsetMatrix[i][j] = subsetMatrix[i-1][j];
            }
        }
        return subsetMatrix[nums.length][sum];
    }
}
