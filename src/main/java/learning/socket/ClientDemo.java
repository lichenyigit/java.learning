package learning.socket;

import java.io.*;
import java.net.Socket;

public class ClientDemo {

    public static void main(String[] args) {
//        String serverName = args[0];
//        int port = Integer.parseInt(args[1]);

        String serverName = "localhost";
        int port = 6066;
        try {
            System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
            while (true) {
                Socket client = new Socket(serverName, port);
                System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
                OutputStream outToServer = client.getOutputStream();

                //将结果返回给服务端
                DataOutputStream out = new DataOutputStream(outToServer);
                out.writeUTF("Hello from " + client.getLocalSocketAddress());

                //客户端接收消息
                InputStream inFromServer = client.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                System.out.println("服务器响应： " + in.readUTF());
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
