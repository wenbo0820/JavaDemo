package design.classloader;

import java.lang.reflect.Method;

/**
 * Created by GongWenBo on 2018/1/31.
 */
public class ClassLoaderDemo {

   public static void main(String[] args) throws Exception {
        Class c = new MyClassLoader("D:\\hello").findClass("design.classloader.Test");
        if (c != null){
            Object obj = c.newInstance();
            /*
             * getDeclaredMethods 获取当前类声明的所有方法，只限当前类，不包括继承的
             * getMethods 只能拿到public方法 （包括继承）
             */
            Method[] methods = c.getDeclaredMethods();
            if (methods!=null && methods.length>0){
                for (Method m : methods){
                    System.out.println(m.getName());
                }
            }
            Method method = c.getDeclaredMethod("say",null);
            method.invoke(obj,null);
        }
   }
}
