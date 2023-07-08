static boolean[][] booleanSubsetMatrix;
public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num:nums) {
            sum += num;
        }
        if(sum % 2 != 0)
            return false;
        booleanSubsetMatrix = new boolean[nums.length+1][(sum/2) + 1];
        findValidSubset(nums, nums.length, sum/2);
        return booleanSubsetMatrix[nums.length][sum];
    }
private static void findValidSubset(int[] arr, int n, int range) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= range; j++) {
                if(i == 0)
                    booleanSubsetMatrix[i][j] = false;
                else if(j == 0)
                    booleanSubsetMatrix[i][j] = true;
                else if(arr[i-1] <= j){
                    booleanSubsetMatrix[i][j] = booleanSubsetMatrix[i-1][j- arr[i-1]] || booleanSubsetMatrix[i-1][j];
                }
                else
                    booleanSubsetMatrix[i][j] = booleanSubsetMatrix[i-1][j];
            }
        }
    }
