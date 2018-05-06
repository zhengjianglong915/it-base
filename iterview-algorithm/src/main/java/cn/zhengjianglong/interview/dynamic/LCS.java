package cn.zhengjianglong.interview.dynamic;

/**
 * 最长公共子序列
 * 公共子串是指连续的公有字符串，公共子序列是指两个字符串都任意删除0个或多个字符后得到的公有字符串，
 * 子序列可以是不连续的。
 * 举个例子吧，有两个字符串，串A为“1 2 3 4 5 6 7”，串B 为“1 3 4 5 8 7”，
 * 很明显，A和B的公共子串为“3 4 5”，A和B的公共子序列可以是 “3 5”，或是“1 3 7”，等等。
 * <p>
 * https://www.cnblogs.com/moongeek/p/7530730.html
 *
 * @author: zhengjianglong
 * @create: 2018-05-06 21:08
 */
public class LCS {
    public static int getLCS(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();

        // 记录状态
        int[][] vals = new int[len1 + 1][len2 + 1];

        for (int i = 0; i < len1; i++) {
            // 二维数组从[1][1]开始进行有效存储
            int fi = i + 1;
            for (int j = 0; j < len2; j++) {
                int fj = j + 1;
                //字符相同
                if (str1.charAt(i) == str2.charAt(j)) {
                    // 满足第一种状况
                    vals[fi][fj] = vals[fi - 1][fj - 1] + 1;
                    //字符不相同
                } else if (str1.charAt(i) != str2.charAt(j)) {
                    //满足第二种状况
                    vals[fi][fj] = Math.max(vals[fi - 1][fj], vals[fi][fj - 1]);
                }
            }
        }
        return vals[len1][len2];
    }

    public static void main(String[] args) {
        String str1 = "ABCBDAB";
        String str2 = "BDCABA";
        System.out.println(getLCS(str1, str2));
    }
}
