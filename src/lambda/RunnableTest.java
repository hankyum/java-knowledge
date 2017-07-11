package lambda;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hguo on 7/11/17.
 */
public class RunnableTest {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("In Java8, lambda expression rocks !!!")).start();

        // Java 8之后：
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));

        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        // 看起来像C++的作用域解析运算符
        features.forEach(System.out::println);


    }
}
