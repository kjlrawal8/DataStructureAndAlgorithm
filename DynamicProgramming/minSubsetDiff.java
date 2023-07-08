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

