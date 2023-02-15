package com.sunk.datastructure.chapter12;

/**
 * 使用非递归的方式完成二分查找
 *
 * @author sunk
 * @since 2023/2/15
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 9};
        System.out.println(search(arr, 5));
        System.out.println(search(arr, 8));
    }

    /**
     * @param arr    待查找的数组集合
     * @param target 目标值
     * @return 返回目标值的下标，不存在是返回 -1
     **/
    public static int search(int[] arr, int target) {
        int resIndex = -1;
        int leftIndex = 0;
        int rightIndex = arr.length - 1;
        int middleIndex;
        int middleValue;

        while (leftIndex <= rightIndex) {
            middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
            middleValue = arr[middleIndex];

            if (middleValue > target) {
                rightIndex = middleIndex - 1;
            } else if (middleValue < target) {
                leftIndex = middleIndex + 1;
            } else {
                resIndex = leftIndex;
                break;
            }
        }

        return resIndex;
    }

}
