class Solution {
    Map<String,Boolean> dpMap = new HashMap<>();
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        int n = s1.length();
        return memoizationScramble(s1, s2);
    }

    private boolean memoizationScramble(String s, String t) {
        if(s.equals(t)) return true;

        int n = s.length();

        String k = s + " " + t;
        if(dpMap.containsKey(k))
            return dpMap.get(k);

        boolean isScramble = false;
        for (int i = 1; i <= n-1; i++) {
            boolean swap = memoizationScramble(s.substring(0,i), t.substring(n-i))
                    && memoizationScramble(s.substring(i), t.substring(0, n-i));

            boolean noSwap = memoizationScramble(s.substring(0,i), t.substring(0,i))
                    && memoizationScramble(s.substring(i), t.substring(i));

            if(swap || noSwap){
                isScramble = true;
                break;
            }
        }
        dpMap.put(k,isScramble);
        return isScramble;
    }


// Recursive Approach
    private static boolean recursiveScramble(String s, String t) {
        if(s.equals(t)) return true;
        if(s.length() <= 1)
            return false;

        int n = s.length();
        boolean isScramble = false;
        for (int i = 1; i <= n-1; i++) {
            boolean swap = recursiveScramble(s.substring(0,i), t.substring(n-i))
                    && recursiveScramble(s.substring(i), t.substring(0, n-i));

            boolean noSwap = recursiveScramble(s.substring(0,i), t.substring(0,i))
                    && recursiveScramble(s.substring(i), t.substring(i));

            if(swap || noSwap){
                isScramble = true;
                break;
            }
        }
        return isScramble;
    }
}

