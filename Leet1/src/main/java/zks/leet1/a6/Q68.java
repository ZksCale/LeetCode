/*
package zks.leet1.a6;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

*/
/*
68. 文本左右对齐
给定一个单词数组 words 和一个长度 maxWidth ，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。

你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。

要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。

文本的最后一行应为左对齐，且单词之间不插入额外的空格。

注意:

单词是指由非空格字符组成的字符序列。
每个单词的长度大于 0，小于等于 maxWidth。
输入单词数组 words 至少包含一个单词。


示例 1:

输入: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
输出:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
示例 2:

输入:words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
输出:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
     因为最后一行应为左对齐，而不是左右两端对齐。
     第二行同样为左对齐，这是因为这行只包含一个单词。
示例 3:

输入:words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"]，maxWidth = 20
输出:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]


提示:

1 <= words.length <= 300
1 <= words[i].length <= 20
words[i] 由小写英文字母和符号组成
1 <= maxWidth <= 100
words[i].length <= maxWidth
 *//*

public class Q68 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        ArrayList<String> ans = new ArrayList<>();
        int p = 0;
        while (p < words.length) {
            //要先确定这一行能存入几个单词(肯定至少为1)
            int width = words[p].length();//已占宽度
            int amount = 1;//这一行能安排下几个单词
            int i = p;
            while (width + amount - 1 <= maxWidth) {
                if (++i >= words.length) break;
                width += words[i].length();
                amount++;
            }//循环结束后,width>maxWidth,amount多数了一个,i也多读了一位
            if (width + amount - 1 > maxWidth) {
                amount--;
                width -= words[i].length() + 1;
            }
            //处理完毕,amount为这一行的单词数量,width为单词字母的个数,p指向这一行的首个单词,i指向这一行的末尾 [p,i)
            int space = maxWidth - width;//space应该均匀地分到单词之间, 如果不能整除时,空格给左边
            int left, divide;
            if (amount > 1) {
                left = space % (amount - 1);
                divide = space / (amount - 1);
            } else {
                left = 0;
                divide = 0;
            }
            StringBuilder line = new StringBuilder();
            for (; p < i - 1; p++) {
                line.append(words[p]);
                line.append(" ".repeat(divide));
                if (left-- > 0) line.append(" ");
            }
            line.append(words[p++]);
            ans.add(new String(line));
        }
        //额外处理末行
        StringBuilder line = new StringBuilder(ans.remove(ans.size() - 1));
        //将它改成左端对齐,将所有长度大于1的空格串缩减为1,然后在末尾填满空格
        for (int i = 1; i < line.length(); ) {
            if (line.charAt(i) == ' ' && line.charAt(i - 1) == ' ') line.deleteCharAt(i);
            else i++;
        }
        line.append(" ".repeat(maxWidth - line.length()));
        ans.add(new String(line));
        return ans;
    }

    @Test
    public void T68() {
        String[] words0 = {"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.",
                "Art", "is", "everything", "else", "we", "do"};
        String[] words1 = {"What", "must", "be", "acknowledgment", "shall", "be"};
        List<String> ans = this.fullJustify(words1, 16);
        for (String s : ans) System.out.println(s);


    }
}
*/
