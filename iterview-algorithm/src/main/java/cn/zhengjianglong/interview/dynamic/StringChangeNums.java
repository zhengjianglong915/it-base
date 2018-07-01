package cn.zhengjianglong.interview.dynamic;

/**
 * 字符串A 变为 字符串B 最小次数
 *
 * @author: zhengjianglong
 * @create: 2018-06-29 09:44
 */
public class StringChangeNums {

    public int minDistance(String A, String B) {
        if ((A == null && B == null) || (A.equals("") && B.equals(""))) {
            return 0;
        }
        if (A == null || A.equals("")) {
            return B.length();
        }
        if (B == null || B.equals("")) {
            return A.length();
        }
        int ALength = A.length() + 1; // 动态规划数组记录的是 某个长度的使用情况
        int BLength = B.length() + 1;

        int[][] dist = new int[ALength][BLength];

        // 初始化
        for (int i = 0; i < ALength; i++) {
            dist[i][0] = i;
        }

        for (int i = 0; i < BLength; i++) {
            dist[0][i] = i;
        }

        for (int i = 1; i < ALength; i++) {
            for (int j = 1; j < BLength; j++) {
                // dist[i-1][j-1] 是，看 i，j所在位置的值是否相等，如果相等直接不用改变，否则+1
                int cost = dist[i - 1][j - 1] + (A.charAt(i - 1) == B.charAt(j - 1) ? 0 : 1);
                // 删除针对字符串A，当前位置的值不要
                int del = dist[i - 1][j] + 1;
                // 插入新的值
                int insert = dist[i][j - 1] + 1;
                // 取最小值
                dist[i][j] = Math.min(del, insert);
                dist[i][j] = Math.min(dist[i][j], cost);
            }
        }
        return dist[A.length()][B.length()];
    }

    public static void main(String[] args) {
        StringChangeNums changeNums = new StringChangeNums();
        int ret = changeNums.minDistance("BA", "ABC");
        System.out.println(ret);
    }
}
