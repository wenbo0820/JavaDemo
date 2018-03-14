package sort;

/**
 * Created by GongWenBo on 2018/1/26.
 */
public class HeapSort implements Sort {
    @Override
    public void sort(int[] array) {
        buildHeap(array);
        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            adjustHeap(array, 0, i);
        }

    }

    private void buildHeap(int[] arr) {
        for (int i = (arr.length >> 1) - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
    }

    private void adjustHeap(int[] arr, int i, int length) {
        for (int k = (i << 1) + 1; k < length; k = (k << 1) + 1) {
            k = (k + 1 < length && arr[k] < arr[k + 1]) ? k + 1 : k;
            if (arr[k] > arr[i]) {
                swap(arr, i, k);
                i = k;
            }else {
                break;
            }
        }
    }
}
