package com.utils;

import java.util.Arrays;

public class SortUtils {

    public static void main(String[] args) {
        int[] datas = {10, 1, 18, 30, 23, 12, 7, 5, 18, 17};
        onBubbleSort(datas);
        onSelectSort(datas);
        onInsertionSort(datas);
    }

    /**
     * 冒泡排序
     *
     * @param datas
     */
    public static void onBubbleSort(int[] datas) {
        int temp;
        System.out.println("冒泡排序原始数据：" + Arrays.toString(datas));
        for (int i = 0; i < datas.length; i++) {
            //优化部分就是j < datas.length - (i + 1)，因为挡轮最大/最小已经被移至最后，所以不用在管他们了
            for (int j = 0; j < datas.length - (i + 1); j++) {
                if (datas[j] > datas[j + 1]) {
                    temp = datas[j];
                    datas[j] = datas[j + 1];
                    datas[j + 1] = temp;
                }
            }
        }
        System.out.println("冒泡排序后的数据：" + Arrays.toString(datas));
    }

    /**
     * 选择排序
     *
     * @param datas
     */
    public static void onSelectSort(int[] datas) {
        for (int i = 0; i < datas.length - 1; i++) {
            int minIndex = i; // 用来记录最小值的索引位置，默认值为i
            for (int j = i + 1; j < datas.length; j++) {
                if (datas[j] < datas[minIndex]) {
                    minIndex = j; // 遍历 i+1~length 的值，找到其中最小值的位置
                }
            }
            // 交换当前索引 i 和最小值索引 minIndex 两处的值
            if (i != minIndex) {
                int temp = datas[i];
                datas[i] = datas[minIndex];
                datas[minIndex] = temp;
            }
            // 执行完一次循环，当前索引 i 处的值为最小值，直到循环结束即可完成排序
        }
        System.out.println("选择排序后的数据：" + Arrays.toString(datas));
    }

    /**
     * 插入排序
     * 1. 从第一个元素开始，该元素可以认为已经被排序
     * 2. 取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 3. 如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 4. 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 5. 将新元素插入到该位置后
     * 6. 重复步骤2~5
     *
     * @param datas 待排序数组
     */
    public static void onInsertionSort(int[] datas) {
        for (int i = 0; i < datas.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (datas[j - 1] <= datas[j])
                    break;
                int temp = datas[j];      //交换操作
                datas[j] = datas[j - 1];
                datas[j - 1] = temp;
            }
        }
        System.out.println("插入排序后的数据：" + Arrays.toString(datas));
    }

}
