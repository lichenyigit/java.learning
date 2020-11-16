package learning.executor.thread;

public class WukongThread  extends  Thread {

    private String name;

    public WukongThread(String name) {

        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name+"说大家好");
    }

    public static void main(String[] args) {
        new WukongThread("T1").start();
    }

}
