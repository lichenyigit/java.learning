package learning;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Lichenyi
 * @date 2017-9-8 0008
 */
public class Test {

    @org.junit.Test
    public void nanoTimeTest() {
        long time = System.nanoTime();
        System.out.println(-time + (time = System.nanoTime()));

        int i = 1;
        a(i);
        System.out.println(i);

    }

    public void a(int i) {
        i++;
        System.out.println(i);
    }

    @org.junit.Test
    public void b() {
        List list = new ArrayList<>();
        Map map1 = new HashMap<>();
        map1.put("id", "1");
        map1.put("name", "name1");
        Map map2 = new HashMap<>();
        map2.put("id", "2");
        map2.put("name", "name2");
        Map map3 = new HashMap<>();
        map3.put("id", "3");
        map3.put("name", "name3");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        System.out.println(JSON.toJSONString(list));
    }

    @org.junit.Test
    public void c() {
        String colorCode = "#FFFFFF,#DDDDDD,#AAAAAA,#888888,#666666,#444444,#000000,\n" +
                "#FFB7DD,#FF88C2,#FF44AA,#FF0088,#C10066,#A20055,#8C0044,\n" +
                "#FFCCCC,#FF8888,#FF3333,#FF0000,#CC0000,#AA0000,#880000,\n" +
                "#FFC8B4,#FFA488,#FF7744,#FF5511,#E63F00,#C63300,#A42D00,\n" +
                "#FFDDAA,#FFBB66,#FFAA33,#FF8800,#EE7700,#CC6600,#BB5500,\n" +
                "#FFEE99,#FFDD55,#FFCC22,#FFBB00,#DDAA00,#AA7700,#886600,\n" +
                "#FFFFBB,#FFFF77,#FFFF33,#FFFF00,#EEEE00,#BBBB00,#888800,\n" +
                "#EEFFBB,#DDFF77,#CCFF33,#BBFF00,#99DD00,#88AA00,#668800,\n" +
                "#CCFF99,#BBFF66,#99FF33,#77FF00,#66DD00,#55AA00,#227700,\n" +
                "#99FF99,#66FF66,#33FF33,#00FF00,#00DD00,#00AA00,#008800,\n" +
                "#BBFFEE,#77FFCC,#33FFAA,#00FF99,#00DD77,#00AA55,#008844,\n" +
                "#AAFFEE,#77FFEE,#33FFDD,#00FFCC,#00DDAA,#00AA88,#008866,\n" +
                "#99FFFF,#66FFFF,#33FFFF,#00FFFF,#00DDDD,#00AAAA,#008888,\n" +
                "#CCEEFF,#77DDFF,#33CCFF,#00BBFF,#009FCC,#0088A8,#007799,\n" +
                "#CCDDFF,#99BBFF,#5599FF,#0066FF,#0044BB,#003C9D,#003377,\n" +
                "#CCCCFF,#9999FF,#5555FF,#0000FF,#0000CC,#0000AA,#000088,\n" +
                "#CCBBFF,#9F88FF,#7744FF,#5500FF,#4400CC,#2200AA,#220088,\n" +
                "#D1BBFF,#B088FF,#9955FF,#7700FF,#5500DD,#4400B3,#3A0088,\n" +
                "#E8CCFF,#D28EFF,#B94FFF,#9900FF,#7700BB,#66009D,#550088,\n" +
                "#F0BBFF,#E38EFF,#E93EFF,#CC00FF,#A500CC,#7A0099,#660077,\n" +
                "#FFB3FF,#FF77FF,#FF3EFF,#FF00FF,#CC00CC,#990099,#770077";
        String[] arr = colorCode.split(",");
        System.out.println(arr.length);
    }

    @org.junit.Test
    public void d() {
        int count = 6;
        for (float x = -6; x <= count; x += 0.5f) {
            for (float y = -6; y <= count; y += 0.5f) {
                System.out.println(x + "," + y + ",0");
            }
        }
    }

    @org.junit.Test
    public void e() {
        for (int i = 1; i < 30; i++) {
            System.out.println(i + "  " + (i * 2 * 2 + 1));
        }
    }

}
