package cn.zhengjianglong.simple.search;

/**
 * kmp�㷨
 * @author zhengjianglong
 * @since 2018-04-03
 */
public class KMP {

    /**
     * ���㲿��ƥ���
     *
     * @param pattern
     * @param next
     */
    public void makeNext(char[] pattern, int next[]) {
        int pIdx, maxSuffixLen; // pIdx:ģ���ַ����±ꣻmaxSuffixLen:���ǰ��׺����
        int m = pattern.length;  // ģ���ַ�������
        next[0] = 0; //ģ���ַ����ĵ�һ���ַ������ǰ��׺����Ϊ0
        for (pIdx = 1, maxSuffixLen = 0; pIdx < m; ++pIdx) //forѭ�����ӵڶ����ַ���ʼ�����μ���ÿһ���ַ���Ӧ��nextֵ
        {
            /**
             * maxSuffixLen ����0 ��ʾǰһ���ַ��Ѿ�����ƥ��
             */
            while (maxSuffixLen > 0 && pattern[pIdx] != pattern[maxSuffixLen]) { //�ݹ�����P[0]������P[q]��������ͬ��ǰ��׺����k
                maxSuffixLen = next[maxSuffixLen - 1];          //�����û��ϵ������ķ��������whileѭ�������δ���ľ������ڣ�ȷʵ�������
            }
            if (pattern[pIdx] == pattern[maxSuffixLen]) //�����ȣ���ô�����ͬǰ��׺���ȼ�1
            {
                maxSuffixLen++;
            }
            next[pIdx] = maxSuffixLen;
        }
    }

    /**
     * KMP�㷨
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
                 * �ƶ�ƥ���ַ���λ��
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
