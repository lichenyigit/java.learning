package learning;

import org.junit.Test;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaTest {

    /**
     * lambda 表达式实现Runable
     */
    @Test
    public void lambdaTest1(){
        //Java8 之前
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is no lambda.");
            }
        }).start();

        //Java8
        new Thread(() -> System.out.println("this is lambda.")).start();
    }

    /**
     * 使用Java 8 lambda表达式进行事件处理
     */
    @Test
    public void lambdaTest2(){
        // Java 8之前：
        JButton show =  new JButton("Show");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Event handling without lambda expression is boring");
            }
        });
        // Java 8方式：
        show.addActionListener((e) -> {
            System.out.println("Light, Camera, Action !! Lambda expressions Rocks");
        });
    }

    /**
     * 使用lambda表达式对列表进行迭代
     */
    @Test
    public void lambdaTest3(){
        //java 8 之前
        List<String> features = Arrays.asList("Lambdas", "Stream API", "zzzz", "xxxx");
        for (String value: features) {
            System.out.println("no lambda value --> " + value);
        }

        //java8 之后
        features.forEach(value -> System.out.println("lambda value --> " + value));

    }

    /**
     * 使用lambda表达式和函数式接口Predicate
     */
    @Test
    public void lambdaTest4(){

        //java 8 之前
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str)->str.startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str)->str.endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str)->true);

        System.out.println("Print no language : ");
        filter(languages, (str)->false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str)->str.length() > 4);

    }


    /**
     * lambda表达式中加入Predicate
     */
    @Test
    public void test5(){
        List names = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        Predicate<String> startwithJ = (n)->n.startsWith("J");
        Predicate<String> fourLetterLong  = (n)->n.length()==4;
        names.stream().filter(startwithJ.and(fourLetterLong)).forEach((n)-> {
            System.out.println("nName, which starts with 'J' and four letter long is : " + n);
        });
    }

    /**
     * Map和Reduce示例
     */
    @Test
    public void test6(){
        // 不使用lambda表达式为每个订单加上12%的税
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            System.out.println(price);
        }

        // 使用lambda表达式
        costBeforeTax.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);
    }

    @Test
    public void test62(){
        // 为每个订单加上12%的税
        // 老方法：
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            total = total + price;
        }
        System.out.println("Total : " + total);

        // 新方法：
        double bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);
    }

    /**
     * 通过过滤创建一个String列表
     */
    @Test
    public void test7(){
        List<String> strList = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp", "A", "b");
        // 创建一个字符串列表，每个字符串长度大于2
        List<String> filtered = strList.stream().filter(x -> x.length()> 2).collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);
    }

    /**
     * 对列表的每个元素应用函数
     */
    @Test
    public void test8(){
        // 将字符串换成大写并用逗号链接起来
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);
    }

    /**
     * 复制不同的值，创建一个子列表
     */
    @Test
    public void test9(){
        // 用所有不同的数字创建一个正方形列表
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);
    }

    /**
     * 计算集合元素的最大值、最小值、总和以及平均值
     */
    @Test
    public void test(){
        //获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }

    public static void filter(List<String> names, Predicate<String> condition) {
        for (String name : names) {
            if (condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }

}
