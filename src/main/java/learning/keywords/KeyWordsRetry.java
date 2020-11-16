package learning.keywords;

public class KeyWordsRetry {

    public static void main(String args[]){
        aa:
        for(int i = 0;i < 10;i++){
            //retry:
            if(i == 5){
                continue aa;
            }
            System.out.println(i);
        }
    }
}
