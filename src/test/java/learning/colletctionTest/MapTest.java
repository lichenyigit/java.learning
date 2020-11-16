package learning.colletctionTest;

import org.junit.Test;

import java.util.*;

/**
 * @author lichenyi
 * @date 2017-9-12-0012.
 */
public class MapTest {

    @Test
    public void hashMapTest1() {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("1", "1");
        System.out.println(199 & 198);
        System.out.println(1 & 2);
        System.out.println(1 << 30);

        Map<String, Object> hashTable = new Hashtable<>();

        Map<String, Object> treeMap = new TreeMap<>();

        Map<String, Object> linkedHashMap = new LinkedHashMap<>();

    }

    @Test
    public void hashMapTest2() {
        //final Map<String, Object> hashMap = new HashMap<>();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < 10000; index++) {
                    //hashMap.put(String.valueOf(index), "1");
                    System.out.println("写入数据" + index);
                }
            }
        }, "写入数据");
        thread1.start();

        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int index = 0;index < 10000 ;index++){
                    hashMap.remove(String.valueOf(index));
                    System.out.println("移除数据"+index);
                }
            }
        }, "移除数据").start();
        */

        //thread1.join();

    }

    static Map<Thread, Integer> map = new HashMap<Thread, Integer>();

    public static void main(String args[]) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            new Thread("HashMap线程安全测试") {
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + ":" + data);
                    map.put(Thread.currentThread(), data);
                    map.put(Thread.currentThread(), data);
                    System.out.println(Thread.currentThread().getName()+":"+data);

                    new A().getDataA();
                    new B().getDataB();
                }
            }.start();
        }
    }

    static class A {
        void getDataA() {
            System.out.println(Thread.currentThread().getName() + "A:" + map.get(Thread.currentThread()));
        }
    }

    static class B {
        void getDataB() {
            System.out.println(Thread.currentThread().getName() + "B:" + map.get(Thread.currentThread()));
        }
    }

    @Test
    public void treeMapTest(){
        Map<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1, "语文");
        treeMap.put(3, "英语");
        treeMap.put(2, "数学");
        treeMap.put(4, "政治");
        treeMap.put(5, "历史");
        treeMap.put(6, "地理");
        treeMap.put(7, "生物");
        treeMap.put(8, "化学");
        for(Map.Entry entry : treeMap.entrySet()){
            System.out.println(entry.getKey()+" - "+entry.getValue());
        }
    }

}
