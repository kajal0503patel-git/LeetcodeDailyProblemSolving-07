class Solution {
    public int trap(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int maxLeft = 0;
    int maxRight = 0;
    int result=0;
    while(left<=right){
    
        if (height[left] <= height[right]) {
            if (height[left] >= maxLeft) {
                maxLeft = height[left];
            } else {
                result += maxLeft - height[left];
            }
            left++;
        } else {
            if (height[right] >= maxRight) {
                maxRight = height[right];
            } else {
                result += maxRight - height[right];
            }
            right--;
        }

    }return result;
}

}