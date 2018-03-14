package sort;

/**
 * Created by GongWenBo on 2018/1/25.
 */
public class InsertionSort implements Sort {
    /*
        原理同扑克牌整理手牌
     */

    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i;
            while (j > 0 && array[j - 1] > temp) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
    }
}
