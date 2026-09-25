import java.util.*;

class Solution {
    private String expr;
    private int pos;

    public List<String> braceExpansionII(String expression) {
        this.expr = expression;
        this.pos = 0;
        Set<String> result = parseConcat();
        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);
        return answer;
    }

    // Handles concatenation: multiplies (cartesian product) consecutive terms
    // until it hits ',' or '}' or end of string.
    private Set<String> parseConcat() {
        List<Set<String>> factors = new ArrayList<>();

        while (pos < expr.length() && expr.charAt(pos) != ',' && expr.charAt(pos) != '}') {
            factors.add(parseTerm());
        }

        // Start with the empty string and cartesian-multiply each factor in.
        Set<String> result = new HashSet<>();
        result.add("");
        for (Set<String> factor : factors) {
            Set<String> next = new HashSet<>();
            for (String prefix : result) {
                for (String word : factor) {
                    next.add(prefix + word);
                }
            }
            result = next;
        }
        return result;
    }

    // A term is either a single letter, or a whole {...} block (a union).
    private Set<String> parseTerm() {
        if (expr.charAt(pos) == '{') {
            pos++; // consume '{'
            Set<String> unionResult = parseUnion();
            pos++; // consume '}'
            return unionResult;
        } else {
            // single lowercase letter
            String letter = String.valueOf(expr.charAt(pos));
            pos++;
            Set<String> singleton = new HashSet<>();
            singleton.add(letter);
            return singleton;
        }
    }

    // Handles union: comma-separated concatenation groups, unioned together.
    private Set<String> parseUnion() {
        Set<String> result = new HashSet<>();
        result.addAll(parseConcat());

        while (pos < expr.length() && expr.charAt(pos) == ',') {
            pos++; // consume ','
            result.addAll(parseConcat());
        }
        return result;
    }
}