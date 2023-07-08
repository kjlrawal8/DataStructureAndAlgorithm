public class LCS {
  // Recursive Version
public static int recursiveLCS(String x, String y, int m, int n) {
        if(m == 0 || n == 0)
            return 0;
        if(x.charAt(m-1) == y.charAt(n-1))
            return 1 + recursiveLCS(x, y, m-1, n-1);
        return Math.max(recursiveLCS(x,y, m-1, n) , recursiveLCS(x, y, m, n-1));
    }
//Memoized
  public static int memoizationLCS(String x, String y, int m, int n) {
        int[][] t = new int[m+1][n+1];
        Arrays.stream(t).forEach(row -> Arrays.fill(row, -1));
        if(m == 0 || n == 0)
            return 0;
        if(t[m][n] != -1)
            return t[m][n];
        else if(x.charAt(m-1) == y.charAt(n-1))
            t[m][n] = 1 + memoizationLCS(x, y, m-1, n-1);
        else
            t[m][n] = Math.max(memoizationLCS(x,y, m-1, n) , memoizationLCS(x, y, m, n-1));
        return t[m][n];
    }
  
// DP
  public static int topDownLCS(String x, String y, int m, int n) {
        int[][] t = new int[m+1][n+1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if(i == 0 || j == 0)
                    t[i][j] = 0;
                else if (x.charAt(i-1) == y.charAt(j-1)) {
                    t[i][j] = 1 + t[i-1][j-1];
                }
                else
                    t[i][j] = Math.max(t[i-1][j], t[i][j-1]);
            }
        }
        return t[m][n];
    }

  
}
