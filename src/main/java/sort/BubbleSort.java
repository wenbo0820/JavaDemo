package sort;

/**
 * Created by GongWenBo on 2018/1/25.
 */
public class BubbleSort implements Sort {

    public void sort(int[] array) {
        boolean isSwapped = true;
        for (int i = array.length - 1; i > 0 && isSwapped; i--) {
            isSwapped = false;
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    isSwapped = true;
                }
            }
        }
    }
}
