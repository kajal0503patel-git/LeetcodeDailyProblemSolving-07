class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);

        int currSum = 0;
        int count = 0;

        for (int num : nums) {
            currSum += num;

            count += prefixCount.getOrDefault(currSum - k, 0);

            prefixCount.put(currSum, prefixCount.getOrDefault(currSum, 0) + 1);

        }
        return count;
    }
}