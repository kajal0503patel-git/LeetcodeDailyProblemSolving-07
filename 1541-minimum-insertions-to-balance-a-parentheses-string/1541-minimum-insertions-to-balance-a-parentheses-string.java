class Solution {
    public int minInsertions(String s) {
        int ans = 0;
        int need = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (need % 2 == 1) {
                    ans++;
                    need--;
                }
                need += 2;
            } else {
                need--;
                if (need == -1) {
                    ans++;
                    need = 1;
                }
            }
        }
        return ans + need;
    }
}