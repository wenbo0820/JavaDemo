package java8;

import java.util.*;

/**
 * Created by GongWenBo on 2018/3/2.
 */
public class Java8Demo {

    public static void main(String[] args) {
        //1. 接口中可以有默认实现

        //2. lambda
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        names.forEach(a -> System.out.print(a));

        //3.函数式接口,只能有一个抽象方法，可以包含default方法
        //任意只包含一个抽象方法的接口，我们都可以用来做成lambda表达式
        //FunctionalInterface 注解只是标记接口，用于编译器检查是否只含一个未实现方法
        Converter<String,Integer> converter = f -> Integer.valueOf(f);

        //4.方法引用和构造引用
        Converter<String,Integer> converter2 = Integer::valueOf;
        names.forEach(String::toUpperCase);
        names.sort(String::compareTo);


    }

    @FunctionalInterface
    interface Converter<F,T>{
        T convert(F from);
        default int getOne(){
            return 1;
        }
    }

}
