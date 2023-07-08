public static int change(int amount, int[] coins) {
        if(amount == 0)
            return 1;
        if(coins.length == 0)
            return 0;
        int[][] t = new int[coins.length + 1][amount+1];
        for (int i = 0; i <= coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if(i == 0)
                    t[i][j] = 0;
                else if (j == 0) {
                    t[i][j] = 1;
                } else if (coins[i-1] <= j)
                    t[i][j] = t[i][j-coins[i-1]] + t[i-1][j];
                else
                    t[i][j] = t[i-1][j];
            }
        }
        return t[coins.length][amount];
    }
