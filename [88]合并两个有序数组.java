//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。 
//
// 
//
// 说明: 
//
// 
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。 
// 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。 
// 
//
// 
//
// 示例: 
//
// 输入:
//nums1 = [1,2,3,0,0,0], m = 3
//nums2 = [2,5,6],       n = 3
//
//输出: [1,2,2,3,5,6] 
// Related Topics 数组 双指针 
// 👍 557 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        } else if (n == 0) {
            return;
        }

        int[] copy_nums1 = new int[m];
        System.arraycopy(nums1, 0, copy_nums1, 0, m);

        int p1 = 0;
        int p2 = 0;
        int cp1 = 0;
        while (cp1 < m && p2 < n) {
            if (copy_nums1[cp1] > nums2[p2]) {
                nums1[p1++] = nums2[p2++];
            } else {
                nums1[p1++] = copy_nums1[cp1++];
            }
        }
        if (cp1 < m) {
            System.arraycopy(copy_nums1, cp1, nums1, p2 + cp1, m - cp1);
        } else {
            System.arraycopy(nums2, p2, nums1, cp1 + p2, n - p2);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

//官方
//class Solution {
//    public void merge(int[] nums1, int m, int[] nums2, int n) {
//        // Make a copy of nums1.
//        int [] nums1_copy = new int[m];
//        System.arraycopy(nums1, 0, nums1_copy, 0, m);
//
//        // Two get pointers for nums1_copy and nums2.
//        int p1 = 0;
//        int p2 = 0;
//
//        // Set pointer for nums1
//        int p = 0;
//
//        // Compare elements from nums1_copy and nums2
//        // and add the smallest one into nums1.
//        while ((p1 < m) && (p2 < n))
//            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];
//
//        // if there are still elements to add
//        if (p1 < m)
//            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
//        if (p2 < n)
//            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
//    }
//}


