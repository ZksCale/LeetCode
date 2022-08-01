package zks.leet1.a7;

/*
75. 颜色分类
给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

必须在不使用库的sort函数的情况下解决这个问题。



示例 1：

输入：nums = [2,0,2,1,1,0]
输出：[0,0,1,1,2,2]
示例 2：

输入：nums = [2,0,1]
输出：[0,1,2]


提示：

n == nums.length
1 <= n <= 300
nums[i] 为 0、1 或 2


进阶：

你可以不使用代码库中的排序函数来解决这道题吗？
你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
public class Q75 {
    //用画笔涂鸦
//    public void sortColors(int[] nums) {
//        int p0 = 0, p1 = 0, p2 = 0;
//        //遇到0时三者都向前进,遇到1时后两者向前进,遇到2时只有p2向前进
//        while (p2 < nums.length) {
//            if (nums[p2] == 0) {
//                nums[p2++] = 2;
//                nums[p1++] = 1;
//                nums[p0++] = 0;
//            } else if (nums[p2] == 1) {
//                nums[p2++] = 2;
//                nums[p1++] = 1;
//            } else nums[p2++] = 2;
//        }
//    }
    //双指针交换
    public void sortColors(int[] nums) {
        int p0 = 0, p2 = nums.length - 1;
        for (int i = 0; i <= p2; ) {
            if (nums[i] == 0) {
                int t = nums[i];
                nums[i++] = nums[p0];
                nums[p0++] = t;
            } else if (nums[i] == 1) i++;
            else {
                int t=nums[i];
                nums[i]=nums[p2];
                nums[p2--]=t;
            }
        }
    }

}
