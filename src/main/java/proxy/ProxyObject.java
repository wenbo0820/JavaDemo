package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by GongWenBo on 2018/3/5.
 */
public class ProxyObject<T> implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       if (method.getName().equals("sayHello")){
           System.out.println("hello");
       }else if(method.getName().equals("show")){
           System.out.println("show run");
       }else {
           System.out.println("Method name : "+ method.getName());
       }
        return null;
    }
}
