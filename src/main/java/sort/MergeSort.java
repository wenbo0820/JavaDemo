package sort;

/**
 * Created by GongWenBo on 2018/1/25.
 */
public class MergeSort implements Sort {
    @Override
    public void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] arr, int left, int right) {
        if (left == right)
            return;
        int mid = (left + right) >> 1;
        mergeSort(arr, left, mid);                            //左边归并
        mergeSort(arr, mid + 1, right);                 //右边归并
        merge(arr, left, mid, right);                        //合并
    }


    /*归并操作*/
    private void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];     //需要辅助数组
        int l = left, r = mid + 1;                    //设定两部分的起始坐标
        int index = 0;                              //temp角标
        while (l <= mid && r <= right)
            temp[index++] = arr[l] < arr[r] ? arr[l++] : arr[r++];
        while (l <= mid)
            temp[index++] = arr[l++];
        while (r <= right)
            temp[index++] = arr[r++];
        for (int i : temp) {
            arr[left++] = i;
        }
    }
}
