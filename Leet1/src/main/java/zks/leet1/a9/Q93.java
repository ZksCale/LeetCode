package zks.leet1.a9;

import java.util.ArrayList;
import java.util.List;

/*
93. 复原 IP 地址
有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。

例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。



示例 1：

输入：s = "25525511135"
输出：["255.255.11.135","255.255.111.35"]
示例 2：

输入：s = "0000"
输出：["0.0.0.0"]
示例 3：

输入：s = "101023"
输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]


提示：

1 <= s.length <= 20
s 仅由数字组成
 */
public class Q93 {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        recursion(ans, s, -1, -1, -1);
        return ans;
    }

    private void recursion(List<String> ans, String s, int a, int b, int c) {
        int l = s.length();
        int left = 0;//剩下几段
        if (a < 0) left = 4;
        else if (b < 0) left = 3;
        else if (c < 0) left = 2;
        else left = 1;
        //递归基
        if(left==1){//已经划分好了,判断是否是有效ip,有效就将它存入ans

        }else {//没划分好,判断后面的长度是否越界
            if(left==4&&(l<4||l>12)) return;
            else if (left == 3 && ((l - a - 1) < 3 || (l - a - 1) > 9)) return;
            else if ((l - b - 1) < 2 || (l - b - 1) > 6) return;
        }
        //一般情况
        if(a<0){//[0,a]
            char n0 = s.charAt(0);
            if(n0=='0'||n0-'0'>2){//[0,0][3,9]

            }else if(n0=='1'){

            }else {//2

            }
        }else if(b<0){//(a,b]

        }else{//(b,c]

        }
    }
    //接受一个s和起点i 从起点读最多三位数 返回所有的可能切割
}
