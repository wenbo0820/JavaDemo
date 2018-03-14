package proxy;

import java.lang.reflect.Proxy;

/**
 * Created by GongWenBo on 2018/3/5.
 */
public class ProxyFactory {

    public static <T> T newInstance(Class<T> target){

        final ProxyObject proxyObject =  new ProxyObject();

        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),new Class[]{target},proxyObject);
    }
}
