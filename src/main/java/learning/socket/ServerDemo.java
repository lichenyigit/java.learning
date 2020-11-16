package learning.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class ServerDemo extends Thread
{
    private ServerSocket serverSocket;

    public ServerDemo(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }

    public void run()
    {
        /*while(true)
        {
            try
            {
                System.out.println("等待远程连接，端口号为：" + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("远程主机地址：" + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());
                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                //out.writeUTF("谢谢连接我：" + server.getLocalSocketAddress() + "\nGoodbye!");
                out.writeUTF("向客户端发送：" + server.getLocalSocketAddress() + "\nGoodbye!");
                server.close();
            }catch(SocketTimeoutException s)
            {
                System.out.println("Socket timed out!");
                break;
            }catch(IOException e)
            {
                e.printStackTrace();
                break;
            }
        }*/
    }

    private void sendMsg(String content, Socket server){
        try
        {
            //向客户端发送数据
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            System.out.println("向客户端发送：" + content);
            out.writeUTF(content);

            //接收客户端的返回数据
            DataInputStream in = new DataInputStream(server.getInputStream());
            System.out.println(in.readUTF());
            server.close();
        }catch(SocketTimeoutException s)
        {
            System.out.println("Socket timed out!");
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String [] args)
    {
        //int port = Integer.parseInt(args[0]);
        int port = 6066;
        try
        {
            ServerDemo serverDemo = new ServerDemo(port);
            System.out.println("等待远程连接，端口号为：" + serverDemo.serverSocket.getLocalPort() + "...");
            while (true){
                Socket server = serverDemo.serverSocket.accept();
                System.out.println("远程主机地址：" + server.getRemoteSocketAddress());
                Scanner xx = new Scanner( System.in );
                System.out.print("enter a number: ");//println换行；print不换行
                int number = xx.nextInt();
                serverDemo.sendMsg(number+"", server);
            }
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
