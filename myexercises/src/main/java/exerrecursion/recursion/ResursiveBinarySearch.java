package exerrecursion.recursion;

/**
 * 二分查找法 递归
 */
public class ResursiveBinarySearch {

    public static void main(String[] args) {
        int[] list = {2, 4, 24, 33, 44, 55, 68, 87, 144};
        int key = 46;
        int result = recursiveBinarySearch(list, key);
        System.out.println(result);
    }

    /**
     * 二分查找法
     *      前提：排好序！！！
     * @param list 要排序的列表
     * @param key  要查找的数
     * @return     在列表中的索引值
     */
    public static int recursiveBinarySearch(int[] list, int key) {
        int low = 0;
        int high = list.length - 1;
        return recursiveBinarySearch(list, key, low, high);
    }

    public static int recursiveBinarySearch(int[] list, int key, int low, int high) {
        if (low > high) {
            return -low-1;
        }
        int mid = (low + high) / 2;
        if (key < list[mid]) {
            return recursiveBinarySearch(list, key, low, mid - 1);
        } else if (key == list[mid]) {
            return mid;
        } else {
            return recursiveBinarySearch(list, key, mid + 1, high);
        }
    }

}
