package zks.leet1.a5;

import org.junit.jupiter.api.Test;

/*
41. 缺失的第一个正数
给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。

请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。


示例 1：

输入：nums = [1,2,0]
输出：3
示例 2：

输入：nums = [3,4,-1,1]
输出：2
示例 3：

输入：nums = [7,8,9,11,12]
输出：1


提示：

1 <= nums.length <= 5 * 10^5
-231 <= nums[i] <= 2^31 - 1
 */
/*
考虑到本题目中nums的长度最大为五十万,在极端情况下,数组中的元素为1,2,3,...,5 * 10^5
所以考虑使用一个bitmap,使得它拥有的bit大于五十万byte[2^16]即可做到,将它称为bitmap
bitmap[0]最左边的第一个bit代表1,第二个bit代表2,第三个bit代表3,以此类推
bitmap应该有的方法是:给一个int,这个int如果不在它表示的范围,直接return,如果在它表示的范围内,将它对应的那一位置1
这样经过一趟O(n)时间的扫描后
对于nums[],从左到右扫描,对于遇到的每个正整数,将它bitmap中对应的位置为1
 */

public class Q41 {
    /* 这种使用bitmap的方法虽然确实符合要求地解决了问题,不过并不够高效,原因在于对于bitmap的遍历虽然是常数意义上的复杂度,不过这个常数非常大
    已经比排序花费更多的时间了
    final private static int RANGE = (int) Math.pow(2, 19);
        private static byte[] bitmap = new byte[RANGE >> 3];
        private static byte[] bit8 = new byte[8];

        static {
            bit8[0] = -128;
            bit8[7] = 1;
            for (int i = 6; i > 0; i--)
                bit8[i] = (byte) (bit8[i + 1] << 1);
        }


        public int firstMissingPositive(int[] nums) {
            clearBitmap();
            int ans = 0;
            for (int n : nums) setBitmap(n);
            label:
            for (int i = 0; i < bitmap.length; i++) {
                if (bitmap[i] != -1) {
                    for (int j = 0; j < 8; j++) {
                        if ((bitmap[i] & bit8[j]) == 0) {
                            //找到了那个最小正整数了,下面把它取出来,
                            ans = i * 8 + j + 1;
                            break label;
                        }
                    }
                }
            }
            return ans;
        }

        public static void clearBitmap() {
            for (int i=0;i<bitmap.length;i++) {
                if (bitmap[i] == 0) continue;
                else bitmap[i] = 0;
            }
        }

        public static void setBitmap(int n) {
            if (n <= 0 || n >= RANGE) return;
            int index = --n / 8;
            int bitNum = n % 8;//[0,7]
            bitmap[index] |= bit8[bitNum];
        }*/
/*答案1: 如果没有限制空间复杂度为常数的话,可以直接引入一个哈希表,将nums中的数字存入表中,然后从1开始寻找最小的不存在与哈希表的正整数,
可以看到是两次扫描一定出结果的,时间复杂度是O(n),满足要求的
由于限制了空间复杂度,在这个问题中,考虑将该数组nums原地改造成一个哈希表,注意到数组的长度为length,如果对每个秩都进行标记,它就能做到哈希表的功能
因此:将nums中所有超出范围的数字都置为length+1
    然后对nums遍历,对于每一个在范围中的数字,将它的值所指向的的元素置为负数,也就是说用负号作为标记,记录了每个秩+1这个正整数是否出现过
    最后寻找答案,在遇到的第一个大于零的元素,它的秩+1就是要找的答案,如果全部都是负数,说明这个数组是从1到length升序排列的,那么nums.length+1就是答案*/

    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {//第一轮扫描
            if (nums[i] <= 0 || nums[i] > len) nums[i] = len + 1;
        }
        for (int i = 0; i < len; i++) {//第二轮扫描
            int n = Math.abs(nums[i]);
            if (n <= len) nums[n - 1] = -Math.abs(nums[n - 1]);//需要注意用负数做标记需要严格地置为负数,n*=-1会在偶数个重复元素时错误地置为正数
        }
        for (int i = 0; i < len; i++) {//寻找答案
            if (nums[i] > 0) return i + 1;
        }
        return len + 1;
    }
    /*
    答案2:置换法(本方法的时间复杂度存疑)
   如果数组中包含 x∈[1,N]，那么恢复后，数组的第 x - 1 个元素为 x
   简单来讲就是做交换,扫描到第i个元素时,它的值如果在范围内,那
     */
//    public int firstMissingPositive(int[] nums) {
//
//    }

    @Test
    public void T41() {
        int[] nums0 = new int[]{1, 1};
        int[] nums1 = new int[]{3, 4, -1, 1};
        int ans0 = this.firstMissingPositive(nums0);
        int ans1 = this.firstMissingPositive(nums1);
        System.out.println(ans0);
        System.out.println(ans1);
    }
}
