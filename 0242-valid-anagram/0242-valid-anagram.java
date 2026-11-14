import java.util.*;

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Integer, Integer> count = new HashMap<>();

        // iterate by code point, not char
        s.codePoints().forEach(cp -> count.merge(cp, 1, Integer::sum));

        for (int cp : t.codePoints().toArray()) {
            Integer c = count.get(cp);
            if (c == null || c == 0) return false;
            count.put(cp, c - 1);
        }
        return true;
    }
}