package jni;

/**
 * Created by GongWenBo on 2018/3/2.
 */
public class JniDemo {

    /*无法执行，未生成相应的dll文件*/
    public static void main(String[] args){
        System.loadLibrary("NativeDemo"); //加载 NativeDemo.dll
        NativeDemo demo = new NativeDemo();
        demo.sayHello(); //该方法为native
    }

}
