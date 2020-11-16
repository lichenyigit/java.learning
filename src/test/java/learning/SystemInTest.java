package learning;

import java.util.Scanner;

public class SystemInTest {

    public static void main(String args[]){
        while (true){
            Scanner xx = new Scanner( System.in );
            System.out.print("enter a number: ");//println换行；print不换行
            int number = xx.nextInt();
            System.out.println("输入的数字为 "+number);
        }
    }

}
