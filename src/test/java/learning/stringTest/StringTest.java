package learning.stringTest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author lichenyi
 * @date 2017-9-13-0013.
 */
public class StringTest {

    @Test
    public void test1() {
        StringBuffer buffer = new StringBuffer();
        String[] args = {};
        for (String s : args) {
            strcat(buffer, s);
        }
        System.out.println(String.format("buffer --> %s", buffer.toString()));

        Integer x = 10;
        increaseOne(x);
        System.out.println(String.format("x -- %s", x));

        String str1 = "1";
        str1cat(str1);
        System.out.println(String.format("main str1 -- " + str1));
    }

    public static void strcat(StringBuffer sb, String s) {
        sb.append(s);
    }

    public static void str1cat(String string) {
        string = string + "2";
        System.out.println(String.format("str1cat str1 -- " + string));
    }

    // 错误示例
    public static void increaseOne(Integer x) {
        x++;
    }


    @Test
    public void test2() {
        StringTest test = new StringTest();
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("username", "David");
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("username", "Nick");
        String user = "David";
        int i = 1;
        changeValue(map1, map2, user, i);

        //如果打印的是：{username=admin}则说明java的对象传递的是地址，如果是：{username=David}则传递的是值
        System.out.println(String.format("map1 -- %s", map1));//{username=admin}

        System.out.println(String.format("map2 -- %s", map2));//{username=Nick}
        //如果打印的是：admin则说明java的String对象传递的是地址，如果是：David则传递的是值
        System.out.println(user);//David
        System.out.println(i + "");//1
    }

    public void changeValue(Map<String, String> map1, Map<String, String> map2, String str, int i) {
        map2 = map1;
        map1.put("username", "Jack");
        map2.put("username", "admin");
        str = "admin";
        i = 10;
    }

    @Test
    public void test3() {
        String a = "a";
        StringBuilder sb = new StringBuilder("iphone");
        foo(sb);
        System.out.println(String.format("sb - %s", sb));
    }

    void foo(StringBuilder builder) {
        builder.append("4");
    }

    @Test
    public void test4() {
        String a = "a";
        StringBuffer sb = new StringBuffer("iphone");
        foo(sb);
        System.out.println(String.format("sb - %s", sb));
    }

    void foo(StringBuffer builder) {
        builder.append("4");
    }

    @Test
    public void test5(){
        char c1 = '中';
        System.out.println(c1);
    }

    //中文转Unicode
    @Test
    public void gbEncoding() {
        String gbString = "测试";
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]);   //转换为16进制整型字符串
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        System.out.println("unicodeBytes is: " + unicodeBytes);
        System.out.println(unicodeBytes);
    }

    @Test
    public void decodeUnicode(){
        String dataStr = "\\u627e\\u4e0d\\u5230\\u6307\\u5b9a\\u7684\\u89c6\\u9891";
        final StringBuffer buffer = new StringBuffer();
        String[] dataArray = dataStr.split("\\\\u");
        for(String str : dataArray){
            if(str.trim().length() < 1){
                continue;
            }
            char letter = (char)Integer.parseInt(str, 16);
            buffer.append(new Character(letter).toString());
        }
        System.out.println(buffer.toString());
    }

    @Test
    public void test6(){
        //User user;
        //user.setName("aa");
    }

    @Test
    public void equalsTest(){
        String a = "1";
        String b = new String("1");
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }

    @Test
    public void dengdengTest(){
        int a = 100, b = 100, c = 1000, d = 1000;
        System.out.println(a == b);
        System.out.println(c == d);

    }

    @Test
    public void stringToJson(){
        String str = "userId=2425623&configId=jNMCK5ct4KN8&startDate=20180304&endDate=20180304&pageSize=2000&startOffset=0";
        StringBuffer result = new StringBuffer("{");
        String[] arr1 = str.split("&");
        for(String kv : arr1){
            String k = kv.split("=")[0];
            String v = kv.split("=")[1];
            result.append("\"");
            result.append(k);
            result.append("\"");
            result.append(":");
            result.append("\"");
            result.append(v);
            result.append("\"");
            result.append(",");
        }
        result.append("}");
        System.out.println(result.toString());
    }

    public static void main(String args[]) {
        a();
    }

    private static void a(){
        b();
    }
    private static void b(){
        c();
    }
    private static void c(){

    }

    @Test
    public void testDate(){
        int num=(int)(Math.random()*8999)+1000+1;
        System.out.println(num);
        long time = System.currentTimeMillis();
        System.out.println(time);
        System.out.println(String.valueOf(time).length());
    }

}
