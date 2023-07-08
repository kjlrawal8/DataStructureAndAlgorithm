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
boolean isPalindrome(String string, int i, int j) {
        while (i < j) {
            if (string.charAt(i) != string.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
