class Solution {
    private Set<String> result = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0, rightRem = 0;

        // Step 1: count how many '(' and ')' must be removed
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) {
                    leftRem--; // matched with a previous '('
                } else {
                    rightRem++; // extra ')'
                }
            }
        }

        // Step 2: backtrack
        dfs(s, 0, 0, 0, leftRem, rightRem, new StringBuilder());
        return new ArrayList<>(result);
    }

    private void dfs(String s, int index, int leftCount, int rightCount,
            int leftRem, int rightRem, StringBuilder sb) {

        // Base case: processed the whole string
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0) {
                result.add(sb.toString());
            }
            return;
        }

        char c = s.charAt(index);
        int len = sb.length();

        // Option 1: remove the current character (if it's a parenthesis we can still remove)
        if (c == '(' && leftRem > 0) {
            dfs(s, index + 1, leftCount, rightCount, leftRem - 1, rightRem, sb);
        } else if (c == ')' && rightRem > 0) {
            dfs(s, index + 1, leftCount, rightCount, leftRem, rightRem - 1, sb);
        }

        // Option 2: keep the current character
        sb.append(c);

        if (c != '(' && c != ')') {
            // letter: just continue
            dfs(s, index + 1, leftCount, rightCount, leftRem, rightRem, sb);
        } else if (c == '(') {
            dfs(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, sb);
        } else if (rightCount < leftCount) {
            // ')' is only valid if there's an unmatched '(' before it
            dfs(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, sb);
        }

        sb.setLength(len); // backtrack
    }
}
