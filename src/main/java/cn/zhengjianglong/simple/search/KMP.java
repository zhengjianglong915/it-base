package cn.zhengjianglong.simple.search;

/**
 * kmp算法
 * @author zhengjianglong
 * @since 2018-04-03
 */
public class KMP {

    /**
     * 计算部分匹配表
     *
     * @param pattern
     * @param next
     */
    public void makeNext(char[] pattern, int next[]) {
        int pIdx, maxSuffixLen; // pIdx:模版字符串下标；maxSuffixLen:最大前后缀长度
        int m = pattern.length;  // 模版字符串长度
        next[0] = 0; //模版字符串的第一个字符的最大前后缀长度为0
        for (pIdx = 1, maxSuffixLen = 0; pIdx < m; ++pIdx) //for循环，从第二个字符开始，依次计算每一个字符对应的next值
        {
            /**
             * maxSuffixLen 大于0 表示前一个字符已经存在匹配
             */
            while (maxSuffixLen > 0 && pattern[pIdx] != pattern[maxSuffixLen]) { //递归的求出P[0]···P[q]的最大的相同的前后缀长度k
                maxSuffixLen = next[maxSuffixLen - 1];          //不理解没关系看下面的分析，这个while循环是整段代码的精髓所在，确实不好理解
            }
            if (pattern[pIdx] == pattern[maxSuffixLen]) //如果相等，那么最大相同前后缀长度加1
            {
                maxSuffixLen++;
            }
            next[pIdx] = maxSuffixLen;
        }
    }

    /**
     * KMP算法
     * @param str
     * @param pattern
     * @return
     */
    public int kmp(String str, String pattern) {
        int[] next = new int[str.length()];
        int strIdx, pIdx;
        makeNext(pattern.toCharArray(), next);

        for (strIdx = 0, pIdx = 0; strIdx < str.length(); ++strIdx) {
            while (pIdx > 0 && pattern.charAt(pIdx) != str.charAt(strIdx)) {
                /**
                 * 移动匹配字符串位置
                 */
                pIdx = next[pIdx - 1];
            }
            if (pattern.charAt(pIdx) == str.charAt(strIdx)) {
                pIdx++;
            }
            if (pIdx == pattern.length()) {
                return strIdx - pattern.length() + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        KMP kmp = new KMP();
        int val = kmp.kmp("ABC", "BC");
        System.out.println(val);
    }
}
