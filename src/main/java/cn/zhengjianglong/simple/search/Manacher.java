package cn.zhengjianglong.simple.search;

/**
 * �������ַ����㷨
 * @author zhengjianglong
 * @since 2018-04-03
 */
public class Manacher {

    public static String getPalindromeLength(String str) {
        // 1.�����µ��ַ���
        // Ϊ�˱����������ĺ�ż�����ĵĲ�ͬ�������⣬��ԭ�ַ����в���'#'�������л��ı����������
        StringBuilder newStr = new StringBuilder();
        newStr.append('#');
        for (int i = 0; i < str.length(); i ++) {
            newStr.append(str.charAt(i));
            newStr.append('#');
        }
        // rad[i]��ʾ��iΪ���ĵĻ��ĵ����뾶��i����Ϊ1�������ַ�����
        int [] rad = new int[newStr.length()];
        // right��ʾ��֪�Ļ����У����ҵı߽������
        int right = -1;
        // id��ʾ��֪�Ļ����У�ӵ�����ұ߽�Ļ��ĵ��е�����
        int id = -1;
        // 2.�������е�rad
        // ����㷨��O(n)�ģ���Ϊrightֻ���������while�ĵ�����������������١�
        for (int i = 0; i < newStr.length(); i ++) {
            // 2.1.ȷ��һ����С�İ뾶
            int r = 1;
            if (i <= right){
                // rad[id] - i + id = mx - i    �п��Կ����� rad[id] - k
                // j = 2 * id - i  i�ĶԳƵ㣬��ͼ��   // ������rad[id-k]
                r = Math.min(rad[id] + id - i , rad[2 * id - i]);
            }
            // 2.2.���Ը���İ뾶
            while (i - r >= 0 && i + r < newStr.length()
                    && newStr.charAt(i - r) == newStr.charAt(i + r)) {
                r++;
            }
            // 2.3.���±߽�ͻ�����������
            if (i + r - 1 > right) {
                right = i + r - 1;
                id = i;
            }
            rad[i] = r;
        }
        // 3.ɨ��һ��rad���飬�ҳ����İ뾶
        int maxLength = 0;
        int idx = 0;
        for (int i = 0; i < rad.length ; i++) {
            if (rad[i] > maxLength) {
                maxLength = rad[i];
                idx = i;
            }
        }
        maxLength --; // �ؼ�����������յĳ���
        StringBuilder sb = new StringBuilder();
        for(int i = idx - maxLength; i <= idx + maxLength; i++){
            if(newStr.charAt(i) != '#')
                sb.append(newStr.charAt(i));
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        Manacher manacher = new Manacher();
        String str = manacher.getPalindromeLength("waabwswfd");
        System.out.println(str);
    }
}
