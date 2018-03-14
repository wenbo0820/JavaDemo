package sort;

/**
 * Created by GongWenBo on 2018/1/26.
 */
public class BucketSort implements Sort {

    @Override
    public void sort(int[] array) {
        int d = 1;
        int[][] bucket = new int[10][array.length];
        int[] count = new int[10];
        int digit;
        for (int i = 1; i <= 3; i++) {
            for (int v : array) {
                digit = (v / d) % 10;
                bucket[digit][count[digit]++] = v;
            }
            int k = 0;
            for (int b = 0; b < 10; b++) {
                if (count[b] == 0)
                    continue;
                for (int w = 0; w < count[b]; w++)
                    array[k++] = bucket[b][w];
                count[b] = 0;
            }
            d *= 10;
        }
    }
}
