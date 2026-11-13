class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int left=0;
        int right1 = nums1.length;
        int right2 = nums2.length;
        int right = right1;
        while (left <= right) {
            int mid1 = (right + left) / 2;
            int mid2 = (right1 + right2+1) / 2 - mid1;
            int maxLeft1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[mid1 - 1];
            int minRight1 = (mid1 == right1) ? Integer.MAX_VALUE : nums1[mid1];
            int maxLeft2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[mid2-1];
            int minRight2 = (mid2 == right2) ? Integer.MAX_VALUE : nums2[mid2];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                if ((right1 + right2) % 2 == 0) {
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2))/2.0;

                } else {
                    return (double) Math.max(maxLeft1, maxLeft2);
                }
            } else if (maxLeft1 > minRight2) {
                right = mid1 - 1;
            } else {
                left = mid1 + 1;
            }

        }
        return 0.0;

    }
}