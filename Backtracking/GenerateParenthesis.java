class BackTrackingSolution {
    public List<String> generateParenthesis(int n) {

        List<String> ans = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        genrateAllParantheses(ans, sb, n, n);
        return ans;

    }
    // n1 = opening bracket
    // n2 = closing bracket count

    public void genrateAllParantheses(List<String> ans, StringBuilder sb, int n1, int n2)
    {
        if(n1 > n2) // if number of opening brackets is greater than closing brackets so returing from recursion
        {
            return; // because for valid parantheses combination n1 <= n2
        }

        if(n1 == 0 && n2 == 0) // if opening and closing brackets becomes 0 means we genrated a valid parantheses
        {

            StringBuilder s = new StringBuilder(sb);
            ans.add(s.toString()); // adding combination
            return; // returning from recursive call
        }

        if(n1 > 0) // if opening brackets greater to 0 append opening bracket in combination
        {
            sb.append('(');
            genrateAllParantheses(ans, sb, n1 - 1, n2);//decrementing opening bracket by 1 and applying recursion
            sb.deleteCharAt(sb.length() - 1);
            // during the backtraking we have to undo all changes we made for to get the next result
        }

        if(n2 > 0) // if closing brackets greater to 0 append closing bracket in combination
        {
            sb.append(')');
            genrateAllParantheses(ans, sb, n1, n2 - 1); //decrementing closing bracket by 1 and applying recursion
            sb.deleteCharAt(sb.length() - 1);

            // during the backtraking we have to undo all changes we made for to get the next result
        }
    }
}
