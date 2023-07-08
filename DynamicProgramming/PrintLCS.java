public static void printLongestCommonSubsequence(String text1, String text2) {
        StringBuilder res = new StringBuilder();
        int m = text1.length();
        int n = text2.length();
        int[][] t = new int[m+1][n+1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if(i == 0 || j == 0)
                    t[i][j] = 0;
                else if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    t[i][j] = 1 + t[i-1][j-1];
                }
                else
                    t[i][j] = Math.max(t[i-1][j], t[i][j-1]);
            }
        }
        int i = m;
        int j = n;
        while(i > 0 && j > 0){
            if(text1.charAt(i-1) == text2.charAt(j-1)) {
                res.append(text1.charAt(i - 1));
                i--; j--;
            } else if (t[i-1][j] > t[i][j-1]) {
                i--;
            } else {
                j--;
            }
        }
        res.reverse();
        System.out.println(res);
    }
