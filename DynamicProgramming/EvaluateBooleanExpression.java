public class Solution {
  public static void main(String[] args) throws Exception {
        System.out.println("BooleanExp = " + countWays(5, "T^F&T"));
    }

static int dp[][][];
    public static int countWays(int N, String S)
    {
         dp = new int[N + 1][N + 1][2];

        for (int row[][] : dp)
            for (int col[] : row)
                Arrays.fill(col, -1);
        return evaluateBoolean(S, 0, N - 1, 1);
    }

// Recursive Approach
public static int evaluateBoolean(String s, int i, int j, int isTrue){
        if(i>j)
            return 0;
        if(i==j)
        {
            // if istrue==1 & str[i] ='T' it means we required a true and got it so return 1 else return 0
            // if istrue==0 & str[i] ='F' it means we required a false and got it so return 1 else return 0
            if (isTrue == 1) {
                return (s.charAt(i) == 'T') ? 1 : 0;
            }
            else {
                return (s.charAt(i) == 'F') ? 1 : 0;
            }
        }
        int ans = 0;
        for(int k=i+1; k<=j-1; k=k+2) {
            int leftT=  evaluateBoolean(s, i, k-1, 1);
            int leftF=  evaluateBoolean(s, i, k-1, 0);
            int rightT= evaluateBoolean(s, k+1, j, 1);
            int rightF= evaluateBoolean(s, k+1, j, 0);

            // Evaluate XOR operation
            if(s.charAt(k)=='^')
            {
                if(isTrue == 1)
                    ans += (leftT * rightF) + (leftF * rightT);
                else
                    ans += (leftT * rightT) + (leftF * rightF) ;
            }
            // Evaluate AND operation
            else if(s.charAt(k)=='&')
            {
                if(isTrue == 1)
                    ans += (leftT * rightT);
                else
                    ans += (leftT * rightF) + (leftF * rightT) + (leftF * rightF);
            }
            // Evaluate OR operation
            else if(s.charAt(k)=='|')
            {
                if(isTrue == 1)
                    ans += (leftT * rightF) + (leftF * rightT) + (leftT * rightT);
                else
                    ans += (leftF * rightF) ;
            }
        }
        return ans;

    }

// Memoization Approach
public static int memoizationEvaluateBoolean(String s, int i, int j, int isTrue){
        if(i>j)
            return 0;
        if(i==j)
        {
            // if istrue==1 & str[i] ='T' it means we required a true and got it so return 1 else return 0
            // if istrue==0 & str[i] ='F' it means we required a false and got it so return 1 else return 0
            if (isTrue == 1) {
                return (s.charAt(i) == 'T') ? 1 : 0;
            }
            else {
                return (s.charAt(i) == 'F') ? 1 : 0;
            }
        }
        if(dp[i][j][isTrue] != -1)
            return dp[i][j][isTrue];

        int ans = 0;

        int leftT, rightT, leftF, rightF;

        for(int k=i+1; k<=j-1; k=k+2) {
            if(dp[i][k-1][1] != -1)
                leftT = dp[i][k-1][1];
            else
                leftT=  memoizationEvaluateBoolean(s, i, k-1, 1);

            if (dp[i][k - 1][0] != -1)
                leftF = dp[i][k - 1][0];
            else
                leftF=  memoizationEvaluateBoolean(s, i, k-1, 0);

            if (dp[k + 1][j][1] != -1)
                rightT = dp[k + 1][j][1];
            else
                rightT= memoizationEvaluateBoolean(s, k+1, j, 1);

            if (dp[k + 1][j][0] != -1)
                rightF = dp[k + 1][j][0];
            else
                rightF= memoizationEvaluateBoolean(s, k+1, j, 0);

            // Evaluate XOR operation
            if(s.charAt(k)=='^')
            {
                if(isTrue == 1)
                    ans += (leftT * rightF) + (leftF * rightT);
                else
                    ans += (leftT * rightT) + (leftF * rightF) ;
            }
            // Evaluate AND operation
            else if(s.charAt(k)=='&')
            {
                if(isTrue == 1)
                    ans += (leftT * rightT);
                else
                    ans += (leftT * rightF) + (leftF * rightT) + (leftF * rightF);
            }
            // Evaluate OR operation
            else if(s.charAt(k)=='|')
            {
                if(isTrue == 1)
                    ans += (leftT * rightF) + (leftF * rightT) + (leftT * rightT);
                else
                    ans += (leftF * rightF) ;
            }
            dp[i][j][isTrue] = ans;
        }
        return ans;

    }
}
