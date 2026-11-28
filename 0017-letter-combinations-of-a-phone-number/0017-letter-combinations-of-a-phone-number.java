import java.util.*;

class Solution {
    private static final String[] MAP = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;

        backtrack(digits, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder current, List<String> result) {
        // Base case: saare digits use ho gaye
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        String letters = MAP[digits.charAt(index) - '0'];

        for (char ch : letters.toCharArray()) {
            current.append(ch);                         // choose
            backtrack(digits, index + 1, current, result); // explore
            current.deleteCharAt(current.length() -
            + 1); // un-choose
        }
    }
}