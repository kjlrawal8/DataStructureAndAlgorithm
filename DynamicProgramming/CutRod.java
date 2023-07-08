public static int cutRod(int[] price, int n) {
        int[][] t = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if(i == 0 || j == 0)
                    t[i][j] = 0;
                else if (i <= j)
                    t[i][j] = Math.max(price[i-1] + t[i][j-i], t[i-1][j]);
                else
                    t[i][j] = t[i-1][j];
            }
        }
        return t[n][n];
    }
