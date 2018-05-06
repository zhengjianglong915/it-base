package cn.zhengjianglong.interview.dynamic;

/**
 * 最长递增子序列
 * https://www.nowcoder.com/questionTerminal/585d46a1447b4064b749f08c2ab9ce66
 * 给定一个长度为N的数组，找出一个最长的单调自增子序列（不一定连续，但是顺序不能乱）。
 * 例如：给定一个长度为6的数组A{5， 6， 7， 1， 2， 8}，则其最长的单调递增子序列为{5，6，7，8}，长度为4.
 *
 * @author: zhengjianglong
 * @create: 2018-05-06 19:09
 */
public class AscentSequence {
    public int findLongest(int[] A) {
        // 记录某个长度中最小的后缀
        int[] B = new int[A.length];
        int end = 0;
        B[end] = A[0];
        for (int i = 1; i < A.length; i ++) {
            if (A[i] > B[end]) {
                B[++end] = A[i];
            } else {
                int idx = binarySearch(B, 0, end, A[i]);
                B[idx] = A[i];
            }
        }
        return end + 1;
    }

    private int binarySearch(int[] array, int start, int end, int target) {
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (array[mid] < target) {
                start = mid + 1;
            } else if (array[mid] > target){
                end = mid;
            } else {
                return mid;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        int[] array = new int[]{2,1,4,3,1,5,6};
        AscentSequence seq = new AscentSequence();
        int len = seq.findLongest(array);
        System.out.println(len);
    }

}
