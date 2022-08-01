package zks.leet1.a7;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/*
71. 简化路径
给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。

在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。

请注意，返回的 规范路径 必须遵循下述格式：

始终以斜杠 '/' 开头。
两个目录名之间必须只有一个斜杠 '/' 。
最后一个目录名（如果存在）不能 以 '/' 结尾。
此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
返回简化后得到的 规范路径 。



示例 1：

输入：path = "/home/"
输出："/home"
解释：注意，最后一个目录名后面没有斜杠。
示例 2：

输入：path = "/../"
输出："/"
解释：从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。
示例 3：

输入：path = "/home//foo/"
输出："/home/foo"
解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
示例 4：

输入：path = "/a/./b/../../c/"
输出："/c"


提示：

1 <= path.length <= 3000
path 由英文字母，数字，'.'，'/' 或 '_' 组成。
path 是一个有效的 Unix 风格绝对路径。
 */
public class Q71 {
    public String simplifyPath(String path) {
        StringBuilder p = new StringBuilder(path);
        this.removeSingleDot(p);
        ArrayList<Integer> doubleDots = this.findDoubleDot(p);
        this.removeDoubleDot(p, doubleDots);
        this.uniquifySlash(p);
        if (p.length() > 1 && p.charAt(p.length() - 1) == '/')
            p.deleteCharAt(p.length() - 1);
        return new String(p);
    }

    //"/./"表示路径本身,在本问题中,直接将它删除
    private void removeSingleDot(StringBuilder p) {
        //删除中间的点,留下连续的斜杠,交给删除斜杠函数处理
        for (int i = 1; i < p.length() - 1; i++) {
            if (p.charAt(i - 1) == '/' && p.charAt(i) == '.' && p.charAt(i + 1) == '/')
                p.deleteCharAt(i);
        }
        if (p.length() > 1 && p.charAt(p.length() - 1) == '.' && p.charAt(p.length() - 2) == '/')
            p.deleteCharAt(p.length() - 1);
    }

    // "/../" 表示左侧的路径向上一级, 在根路径向上一级依然是根路径,注意,"/.."结尾是完全可能的,这时也要正确的做删除
    //这个方法负责找到p中的"/..",注意是严格的两个点,多或少都不行,由于p中可能有多个"..",所以查询应该返回一个数组
    //返回的数组中的元素i语义是: i指向"/.."结构中的斜杠, 元素i在返回数组中升序排列
    private ArrayList<Integer> findDoubleDot(StringBuilder p) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= p.length() - 3; i++) {
            if (p.charAt(i) == '/' && p.charAt(i + 1) == '.' && p.charAt(i + 2) == '.') {
                if (i + 3 == p.length() || p.charAt(i + 3) == '/') {
                    ans.add(i);//找到了,加入答案
                    i += 2;//前进两步,越过两个点
                }
            }
        }
        return ans;
    }


    //这个函数负责将处理p,让".."正确地做到"返回目录上一级"
    //约定: 参数i是最靠左的"/.."格式的返回上一级操作符, 并且,串p已经删除过单个点/./了
    private void removeDoubleDot(StringBuilder p, ArrayList<Integer> ints) {
        //删除..和一层文件目录后,p的长度会缩减, ints中的坐标也需要跟着减小
        while (ints.size() > 0) {
            int i = ints.remove(0);
            //删除i后面的两个点
            p.delete(i + 1, i + 3);
            int offset = 2;
            int k = i;
            while (--k > 0) {
                if (p.charAt(k) != '/') {
                    int j = k;
                    while (--j > 0) if (p.charAt(j) == '/') break;
                    //要删除的区间为:[j+1,k+1)
                    p.delete(j + 1, k + 1);
                    offset += k - j;
                    break;
                }
            }
            //ints中剩余的元素减掉offset
            for (int j = 0; j < ints.size(); j++) ints.set(j, ints.get(j) - offset);
        }
    }

    //将任意多个连续的斜杠修剪为1个,其余部分不变
    private void uniquifySlash(StringBuilder p) {
        for (int i = 1; i < p.length(); ) {
            if (p.charAt(i - 1) == '/' && p.charAt(i) == '/') {
                p.deleteCharAt(i);
            } else i++;
        }
    }

    @Test
    public void T71() {
        String s = "/a/./b/../../c/";
        String simplePath = this.simplifyPath(s);
        System.out.println(simplePath);
    }
}




























