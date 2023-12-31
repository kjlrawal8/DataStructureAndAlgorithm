public int minCut(String s) {
        int n = s.length();
        mcmDpMatrix = new int[n+1][n+1];
        Arrays.stream(mcmDpMatrix).forEach(row -> Arrays.fill(row, -1));
        return minCutSolve(s, 0, n-1);
    }

    private int minCutSolve(String s, int i, int j) {
        if(i >= j || isPalindrome(s, i, j))
            return 0;
        if(mcmDpMatrix[i][j] != -1)
            return mcmDpMatrix[i][j];

        mcmDpMatrix[i][j] = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
            if(isPalindrome(s,i,k)){
                int temp = Math.min(mcmDpMatrix[i][j], minCutSolve(s, i, k) + minCutSolve(s,k+1, j) + 1);
                mcmDpMatrix[i][j] = temp;
            }
        }
        return mcmDpMatrix[i][j];
    }

private static int recursiveMinCut(String s, int i, int j) {
        if (i >= j)
            return 0;
        if(isPalindrome(s,i,j))
            return 0;
        int ans = Integer.MAX_VALUE, temp;
        for (int k = i; k < j; k++) {
            temp = recursiveMinCut(s, i, k) + recursiveMinCut(s, k+1, j) + 1;
            ans = Math.min(temp, ans);
        }
        return ans;
    }

private static int memoizedMinCut(String s, int i, int j) {
        if (i >= j)
            return 0;
        if(isPalindrome(s,i,j))
            return 0;
        if(mcmDpMatrix[i][j] != -1)
            return mcmDpMatrix[i][j];
        mcmDpMatrix[i][j] = Integer.MAX_VALUE;
        int temp;
        for (int k = i; k < j; k++) {
            temp = memoizedMinCut(s, i, k) + memoizedMinCut(s, k+1, j) + 1;
            mcmDpMatrix[i][j] = Math.min(temp, mcmDpMatrix[i][j]);
        }
        return mcmDpMatrix[i][j];
    }

private static int optimizedMemoizationMinCut(String s, int i, int j) {
        if (i >= j)
            return 0;
        if(isPalindrome(s,i,j))
            return 0;
        if(mcmDpMatrix[i][j] != -1)
            return mcmDpMatrix[i][j];
        mcmDpMatrix[i][j] = Integer.MAX_VALUE;
        int temp, left, right;
        for (int k = i; k < j; k++) {
            if(mcmDpMatrix[i][k] != -1)
                left = mcmDpMatrix[i][k];
            else
                left = optimizedMemoizationMinCut(s, i, k);

            if(mcmDpMatrix[k+1][j] != -1)
                right = mcmDpMatrix[k+1][j];
            else
                right = optimizedMemoizationMinCut(s, k+1, j);

            temp = left + right + 1;
            mcmDpMatrix[i][j] = Math.min(temp, mcmDpMatrix[i][j]);
        }
        return mcmDpMatrix[i][j];
    }

boolean isPalindrome(String string, int i, int j) {
        while (i < j) {
            if (string.charAt(i) != string.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
