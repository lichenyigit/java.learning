package learning.colletctionTest;

import org.junit.Test;

import java.util.*;

/**
 * @author Lichenyi
 * @date 2017-9-13 0013
 */
public class ListTest {

    @Test
    public void arrayListTest(){
        List<String> list = new ArrayList<String>();//数组
        list.add("a");
        list.add("b");
        list.add("d");
        list = new LinkedList<>();//双端链表

        list = new Vector<>();//线程安全

        list = new Stack<>();

    }

}
