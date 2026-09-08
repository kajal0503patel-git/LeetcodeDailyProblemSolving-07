class Solution {
    public void rotate(int[] nums, int k) {
        int p = nums.length;
         k = k % p;
        int[] result = new int[p];
       

        for (int i = 0; i < p; i++) {
            result[(i + k) % p] = nums[i];
        }
        System.arraycopy(result, 0, nums, 0,p);

    }

}