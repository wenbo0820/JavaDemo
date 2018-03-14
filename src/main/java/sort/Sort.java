package sort;

/**
 * Created by GongWenBo on 2018/1/25.
 */
public interface Sort {

    void sort(int[] array);

    default void swap(int[] array, int i, int j) {
        if (i != j) {
            array[i] = array[i] ^ array[j];
            array[j] = array[i] ^ array[j];
            array[i] = array[i] ^ array[j];
        }
    }
}
