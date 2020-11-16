package learning.util;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.CRC32;

/**
 * @author Lichenyi
 * @date 2017-7-10
 */
public class USECRC32 {

    private static Map<String, String> result = new HashMap<>();

    public static void main1(String args[]) {
        CRC32 crc32 = new CRC32();
        int count = 1000;
        String[] aa = new String[count];
        for(int i = 0;i < count;i++){
            aa[i] = i+"";
        }
        for (String str : aa) {
            crc32.update(str.getBytes());
            long value = crc32.getValue();
            long key = crc32.getValue()%20;
            //System.out.println(key);
            setResult(key+"");
        }
        System.out.println(result);

        int min = 0;
        int max = 0;
        for(Map.Entry<String, String> entry : result.entrySet()){
            int key = Integer.parseInt(entry.getKey());
            String valu = entry.getValue().toString();
            int valC = valu.split(",").length;
            if(min == 0){
                min = valC;
            }
            if(max == 0){
                max = valC;
            }
            if(valC < min)
                min = valC;
            if(valC > max)
                max = valC;
        }
        System.out.println(min);
        System.out.println(max);
    }

    public static void setResult(String key){
        String value = result.get(key);
        if(value != null){
            value += key+",";
        }else{
            value = key+",";
        }
        result.put(key, value);
    }

    public static void core(){
        CRC32 crc32 = new CRC32();
        crc32.update("adsfadf".getBytes());
        long key = crc32.getValue()%20;
        System.out.println(key);
    }

    public static void main(String args[]) {
        Long begin = System.currentTimeMillis();
        core();
        Long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }

}
