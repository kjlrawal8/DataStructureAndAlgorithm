public int minInsertions(String s) {
        String revStr = new StringBuilder(s).reverse().toString();
        if(s.length() == 0 || s.length() == 1 || s.equals(revStr))
            return 0;
        int m = s.length();
        int[][] dp = new int[m+1][m+1];
        for (int i = 0; i < m+1; i++) {
            for (int j = 0; j < m+1; j++) {
                if(i == 0 || j == 0)
                    dp[i][j] = 0;
                else if (s.charAt(i-1) == revStr.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return m - dp[m][m];
    }
