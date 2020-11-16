package learning;

import org.junit.Test;

/**
 * @author Lichenyi
 * @date 2017-9-13 0013
 */
public class OperatorTest {

    @Test
    public void test1(){
        System.out.println(String.format("0xff --> %s", 0xff));//16进制数
        System.out.println(String.format("Integer.toBinaryString((int)0xff)) --> %s", Integer.toBinaryString((int)0xff)));//16进制数
        System.out.println(String.format("Integer.toHexString(255)) --> %s", Integer.toHexString(255)));
        System.out.println(String.format("0xff >>> 7 --> %s", 0xff >>> 7));
        System.out.println(String.format("(byte) 0xff --> %s", (byte) 0xff));
        System.out.println(String.format("(int)((byte) 0xff) --> %s", (int)((byte) 0xff)));
        System.out.println(String.format("Integer.toBinaryString((int)((byte) 0xff)) --> %s", Integer.toBinaryString((int)((byte) 0xff))));
        System.out.println(String.format("(((byte) 0xff) >>> 7)) --> %s", (((byte) 0xff) >>> 7)));
        System.out.println(String.format("(byte) (((byte) 0xff) >>> 7)) --> %s", (byte) (((byte) 0xff) >>> 7)));
        System.out.println("");
        int i = 1;
        System.out.println("带符号左移");
        System.out.println(String.format("i << 1 --> %s", i << 1));
        System.out.println(String.format("i << 2 --> %s", i << 2));
        System.out.println(String.format("i << 3 --> %s", i << 3));
        System.out.println("带符号右移");
        i = -1;
        System.out.println(String.format("i >> 1 --> %s", i >> 1));
        System.out.println(String.format("i >> 2 --> %s", i >> 2));
        System.out.println(String.format("i >> 3 --> %s", i >> 3));
        System.out.println("无符号右移");
        i = i >> 3;
        System.out.println(String.format("i >>> 2 --> %s", i >>> 2));
        System.out.println(String.format("i >>> 3 --> %s", i >>> 3));
        System.out.println(String.format("i >>> 4 --> %s", i >>> 4));
        System.out.println(String.format("i >>> 1 --> %s", i >>> 1));

    }

    @Test
    public void binaryTest(){
        System.out.println(Integer.toBinaryString(8));
    }

}
