package sort;

/**
 * Created by GongWenBo on 2018/1/25.
 */
public class ShellSort implements Sort {

    /*
        实质是插入排序的改进
     */

    @Override
    public void sort(int[] array) {
        for (int incre = array.length >> 1; incre > 0; incre >>= 1) {
            /* 插入排序 begin*/
            for (int i = incre; i < array.length; i++) {
                int temp = array[i];
                int j = i;
                while (j >= incre && array[j - incre] > temp) {
                    array[j] = array[j - incre];
                    j -= incre;
                }
                array[j] = temp;
            }
             /* 插入排序 end*/
        }
    }
}
