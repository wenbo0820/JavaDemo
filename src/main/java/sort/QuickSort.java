package sort;

/**
 * Created by GongWenBo on 2018/1/26.
 */
public class QuickSort implements Sort {
    @Override
    public void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }


    private void sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int temp = arr[left];
        int l = left, r = right;
        while (l < r) {
            while (arr[r] >= temp && l < r) {
                r--;
            }
            while (arr[l] <= temp && l < r) {
                l++;
            }
            if (l < r) {
                swap(arr, l, r);
                r--;
            }
        }
        swap(arr, left, r);
        sort(arr, left, r - 1);
        sort(arr, r + 1, right);
    }
}
