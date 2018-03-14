package concurrent;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * Created by GongWenBo on 2018/3/5.
 */
public class FutureTaskDemo {

    public static void main(String[] args){
        ExecutorService executor = Executors.newCachedThreadPool();
        FutureTask<String> task = new FutureTask<>(() -> "ahello");
        Future a = executor.submit(task);
        Future b = executor.submit(() -> "ahello");
        executor.shutdown();
        try {
            System.out.println(task.get());
            System.out.println(a.get());
            System.out.println(b.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
