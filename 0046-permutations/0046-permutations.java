import java.util.*;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        // A full-length path is one complete permutation
        if (current.size() >= nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            used[i] = true;                          // choose
            current.add(nums[i]);
            backtrack(nums, used, current, result);  // explore
            current.remove(current.size() - 1);      // un-choose
            used[i] = false;
        }
    }
}