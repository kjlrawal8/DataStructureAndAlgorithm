static boolean[][] booleanSubsetMatrix;
public static int findMin(int arr[], int n) {
        int range = 0;
        for ( int num: arr) {
            range += num;
        }
        booleanSubsetMatrix = new boolean[n+1][range + 1];
        return minSubsetDiff(arr, n, range);
    }

    private static int minSubsetDiff(int[] arr, int n, int range) {
        findValidSubset(arr, n, range);
        int mn = Integer.MAX_VALUE;
            for (int j = 0; j <= range/2 ; j++) {
                if(booleanSubsetMatrix[n][j]){
                    mn = Math.min(mn, range - 2*j);
                }
            }
        return mn;
    }
