package sort;

/**
 * Created by GongWenBo on 2018/1/25.
 */
public class SelectionSort implements Sort {

    @Override
    public void sort(int[] array) {
        int pos;
        for (int i = array.length - 1; i > 0; i--) {
            pos = 0;
            for (int j = 1; j <= i; j++) {
                if (array[pos] < array[j]) {
                    pos = j;
                }
            }
            swap(array, pos, i);
        }
    }
}
