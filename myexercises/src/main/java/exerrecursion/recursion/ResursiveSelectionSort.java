package exerrecursion.recursion;

import org.junit.Test;

public class ResursiveSelectionSort {

    /**
     * 递归方法特点：
     *      1、使用if-else或switch语句会导致不同的情况。
     *      2、一个或多个基础情况（最简单的情况）用来停止递归。
     *      3、每次递归调用都会简化问题，让它不断接近基础情况，知道它变成基础情况为止。
     *      通常，要使用递归接近问题，就要将这个问题分解为子问题。
     */

    /**
     * *******************************************************选择排序************************************************
     * 步骤：
     *      1、在列表中找到最小值，然后将它与第一个数互换位置。
     *      2、忽略第一个数，在剩余的列表中找到最小值，然后与第二个数互换位置。
     *      3、同理，递归调用，排序
     * @param list 所需排序的集合
     * @param low  从哪一位开始
     * @param high 到哪一位结束（最高位）
     * **************************************************************************************************************
     */
    public double[] selectionSort(double[] list, int low, int high) {
        if (low < high) {
            int beginIndex  = low; //记录第一位的索引值
            double min = list[low]; //默认第一位是最小值
            //找到后面数的最小值
            for (int i = low + 1; i <= high; i++) {
                if (list[i] < min) {
                    min = list[i]; //最小值
                    beginIndex = i; //最小值所在索引
                }
            }
            //实际上，下面两部是将第一位和最小值为互换
            list[beginIndex] = list[low]; //将第一位的值赋给最小值的位置
            list[low] = min; //将最小值赋到第一位的位置
            //递归调用
            selectionSort(list, low+1, high);
        }
        return list;
    }

    @Test
    public void testselectionSort() {
        //测试选择排序
        double[] randomList = {3.2, 33.3, 53.8, 1.3, 21.9, 66.2, 99.0, 45.5, 74.7, 15.3};
        randomList = selectionSort(randomList, 0, randomList.length-1);
        for (int i = 0; i < randomList.length; i++) {
            System.out.println(randomList[i]);
        }
    }


}
