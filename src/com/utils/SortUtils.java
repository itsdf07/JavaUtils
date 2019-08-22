package com.utils;

public class SortUtils {

    public static void main(String[] args) {
        onBubbleSort();
    }

    /**
     * 冒泡排序
     */
    public static void onBubbleSort() {
        int temp;
        int[] datas = {10, 1, 18, 30, 23, 12, 7, 5, 18, 17};
        System.out.print("原始数据：");
        for (int data :
                datas) {
            System.out.print(data + " ");
        }
        for (int i = 0; i < datas.length; i++) {
            //优化部分就是j < datas.length - (i + 1)，因为挡轮最大/最小已经被移至最后，所以不用在管他们了
            for (int j = 0; j < datas.length - (i + 1); j++) {
                if (datas[j] < datas[j + 1]) {
                    temp = datas[j];
                    datas[j] = datas[j + 1];
                    datas[j + 1] = temp;
                }
            }
        }
        System.out.println();
        System.out.print("冒泡排序后的数据：");
        for (int data :
                datas) {
            System.out.print(data + " ");
        }
    }
}
