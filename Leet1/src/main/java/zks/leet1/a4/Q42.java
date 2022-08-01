package zks.leet1.a5;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

/*
42. 接雨水
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。



示例 1：



输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
示例 2：

输入：height = [4,2,0,3,2,5]
输出：9


提示：

n == height.length
1 <= n <= 2 * 10^4
0 <= height[i] <= 10^5
 */
public class Q42 {
    /*
        接雨水的问题,第一反应就是模拟下雨,一层一层地下雨,记录下每层被"接住"多少雨水
        从第零层开始,一层一层地试图装水,设定左右两个指针,左指针指向构成一个装水的容器的左侧实心块,右指针指向右侧实心块,中间的部分就是这个单层容器能够装下水的面积了
        位于数组秩为-1和length的两个哨兵,将它们的高度看做无穷小,左指针需要指向这一层的一个实心块,它紧邻的右边必须是空的,右指针则是这段空气后的下一个实心块
        因为一个实心块确实是可能左右两端都有积水的,所以每次计算完面积,左指针从右指针的地方出发,当试图找下一个指针却已经超过范围时,这一层结束
        循环终止条件:试图装水的高度超过了最高柱子的高度
        时间复杂度:O(n*h) 这个效率是非常一般的
        测试会超过时间限制,需要更高明的策略

        public int trap(int[] height) {
            int len = height.length;
            int waterHeight = 1;
            int ans = 0;
            while (true) {//水从低到高
                //每一轮循环应该计算出一层水的"面积"
                int sum = 0;//用来装每层的水
                int l = -1, r = len;//l与r从两端出发,分别找到左右两端遇到的第一个"实心块"
                while (++l < len) if (waterHeight <= height[l]) break;
                while (--r >= 0) if (waterHeight <= height[r]) break;
                //如果全是空心快,l==len,r==0
                if (l == len && r == 0) break;
                //如果是单个实心块,l==r
                if (l == r) break;
                //如果是连着的两个实心块,l+1==r
                if (l + 1 == r) break;
                //以上这三种条件发生时,外圈循环可以退出了,已经不会再有可积水的区域出现了
                for (l++; l < r; l++) {//想象从l处开始每遇到一个空的格子就向其中倒水,这里倒水就简明地令sum++
                    if (height[l] < waterHeight) sum++;
                }
                ans += sum;
                waterHeight++;//每层循环结束后,水升高一层
            }
            return ans;
        }*/
    /*
    考虑这个数组的左端,秩为-1的这个柱子看做负数,那么从h[0]开始向右,如果一直是上楼梯一般地向上爬的话,这段向右的部分要么横着走要么向上走,这部分是不能积水的
    对称的,右端向左爬楼梯的这部分也可以将其扔掉不要,因为它接的雨水面积为0
     这样切割分治是可以解决的
    public int trap(int[] height) {
        return trap(height, 0, height.length - 1);
    }

    public int trap(int[] height, int l, int r) {//l与r为左柱与右柱
        //尝试进行修剪
        for (; l < r; l++) if (height[l] > height[l + 1]) break;
        for (; l < r; r--) if (height[r - 1] < height[r]) break;
        //判断是否是已经到了收敛情况,l>=r
        if (l >= r) return 0;
        //判断是否能够分割,如果l和r之间不存在比它们中的较小者更大的柱子,那么它就是不能分割的了,可以直接计算面积
        int mid = -1;//切分点
        //在(l,r)之间找到最高的那个柱子,如果它比lr中的某个柱子更高或者一样高,那么就从那里分割,否则就不需要分割了
        int maxHeight = 0;
        for (int i = l + 1; i < r; i++) {
            if (maxHeight < height[i]) {
                maxHeight = height[i];
                mid = i;
            }
        }
        int minHeightOfLR = Math.min(height[l], height[r]);
        if (maxHeight >= minHeightOfLR) {//分割
            return trap(height, l, mid) + trap(height, mid, r);
        } else {//不分
            int sum = 0;
            for (int i = l + 1; i < r; i++) sum += minHeightOfLR - height[i];
            return sum;
        }
    }
*/
    /*
    答案1:动态规划
    对于数组height,想象一束光从左侧照过来,靠左的高柱子会在右边比它矮的柱子上方留下阴影,用一个数组leftMaxHeight记录下来柱子和影子
    对称的,右侧照来平行光,用rightMaxHeight记录下来柱子和影子
    两个阴影重叠的部分其实就是接雨水的面积

    对于左右两端柱子之外的任何一个柱子i, 如果能知道i向左的最高柱子和向右的最高柱子分别多高,就能知道柱子i的头上面有多少格的积水了,而对于
    这个问题来说,每个i都遍历左右确实会有很多重复的运算,用动态规划可以两遍扫描完成这个问题,然后第三遍得出面积

    public int trap(int[] height) {
        int length = height.length;
        int[] leftMaxHeight = new int[length];
        int[] rightMaxHeight = new int[length];
        int max = 0;
        for (int i = 0; i < length - 1; i++) {
            max = Math.max(max, height[i]);
            leftMaxHeight[i] = max;
        }
        max = 0;
        for (int i = length - 1; i > 0; i--) {
            max = Math.max(max, height[i]);
            rightMaxHeight[i] = max;
        }
        int sum = 0;
        //下面就是查表计算面积了
        for (int i = 1; i < length - 1; i++)
            sum += Math.min(leftMaxHeight[i], rightMaxHeight[i]) - height[i];
        return sum;
    }
    */
    /*
    使用一个辅助栈,这个栈从栈底向栈顶单调递减的,用它就可以计算接水的面积
     */
    public int trap(int[] height) {
        LinkedList<Integer> list = new LinkedList<>();//栈中保存秩,只能对首元素进行操作,所以可以看成一个栈
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            if (list.isEmpty()) {
                list.addFirst(i);
            } else {
                if (height[list.getFirst()] >= height[i]) list.addFirst(i);
                else {//list中的第二个元素和height[i]作为左右柱,高度为二者较矮的那个柱子减去list.getFirst()
                    int i0 = list.removeFirst();
                    if (list.isEmpty()) {
                        list.addFirst(i);
                        continue;
                    }
                    int minH = Math.min(height[list.getFirst()], height[i]);
                    sum += (i - 1 - list.getFirst()) * (minH - height[i0]);

                }
            }
        }
        return sum;
    }

    @Test
    public void T42() {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(Arrays.toString(height));
        System.out.println(this.trap(height));
    }
}
