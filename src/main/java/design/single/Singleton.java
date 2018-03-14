package design.single;

/**
 * Created by GongWenBo on 2018/1/31.
 */
public class Singleton {

    private volatile static Singleton instance;

    private Singleton() {
    }

    /* double check */
    public static Singleton getInstance(){
        if (instance == null){
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}
