package proxy;

import java.util.concurrent.FutureTask;

/**
 * Created by GongWenBo on 2018/3/5.
 */
public class ProxyDemo {

    public static void main(String[] args){
        TestInterface proxy = ProxyFactory.newInstance(TestInterface.class); //只能代理interface
        proxy.sayHello();
        proxy.show();

        ProxyFactory.newInstance(Runnable.class).run();



    }

}
