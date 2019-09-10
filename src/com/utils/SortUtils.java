package com.utils;

import java.util.Arrays;

public class SortUtils {

    public static void main(String[] args) {
        onBubbleSort();
        onSelectSort();
    }

    /**
     * 冒泡排序
     */
    public static void onBubbleSort() {
        int temp;
        int[] datas = {10, 1, 18, 30, 23, 12, 7, 5, 18, 17};
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
     */
    public static void onSelectSort() {
        int[] datas = {10, 1, 18, 30, 23, 12, 7, 5, 18, 17};
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


}
