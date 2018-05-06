package cn.zhengjianglong.interview.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长连续序列
 * 找出无序数组中的最长连续序列的长度：例如数组[1, 23, 2, 300, 3, 9, 4, 5, 90]，最长连续序列为：1，2，3，4，5，因此返回长度为 5。
 *
 * @author: zhengjianglong
 * @create: 2018-05-06 15:59
 */
public class LongestContinuousSequence {

    /**
     * 最长连续子序列 采用快排思想
     *
     * @param array
     *
     * @return
     */
    public int longestContinuouSequence(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        quickSort(array, 0, array.length - 1);

        int maxLen = 1;
        int curLen = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] != array[i - 1] + 1) {
                curLen = 1;
            } else {
                curLen++;
            }

            if (curLen > maxLen) {
                maxLen = curLen;
            }
        }
        return maxLen;
    }

    /**
     * ﻿使用hashMap记录出现的数字旁边有几个连续的数值，
     * 每次只要判断当前数值前后的值是否出现在hashMap中并获取他们的长度，以此计算当前数值长度。
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        // 某个值附近有连续的几个数字
        Map<Integer, Integer> map = new HashMap<>();
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                continue; // 已经存在了
            }
            // 左边数连续大小
            int left = map.containsKey(nums[i] - 1) ? map.get(nums[i] - 1) : 0;
            // 右边的数连续的大小
            int right = map.containsKey(nums[i] + 1) ? map.get(nums[i] + 1) : 0;
            int sum = left + right + 1;

            len = Math.max(len, sum);
            // left 到right之间的值肯定是出现过了，所以出现连续的值应该是在左右两边，只要更新左右两边边界的值就可以
            map.put(nums[i] - left, sum);  // 设置边界数值的值
            map.put(nums[i] + right, sum);
        }
        return len;
    }

    private void quickSort(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }

        int lt = low, gt = high, i = low + 1;
        int val = array[low];
        while (i <= gt) {
            if (array[i] < val) {
                swap(array, lt++, i++);
            } else if (array[i] > val) {
                swap(array, gt--, i);
            } else {
                i++;
            }
        }

        quickSort(array, low, lt - 1); // 注意要-1
        quickSort(array, gt + 1, high); // 注意需要+1
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        LongestContinuousSequence seq = new LongestContinuousSequence();
        int result = seq.longestContinuouSequence(new int[] {3, 5, 1, 8, 4});
        System.out.println(result);
    }

}
