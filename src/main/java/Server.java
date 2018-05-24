import java.net.*;
import java.io.*;

public class Server extends Thread
{
    private ServerSocket serverSocket;

    public Server(int port) throws IOException
    {
        //服务器实例化一个ServerScoket对象，表示通过服务器的端口通信
        serverSocket = new ServerSocket(port);
        //指定超时传送的时间
        serverSocket.setSoTimeout(10000);
    }

    public void run()
    {
        while(true)
        {
            try
            {
                System.out.println("等待远程连接，端口号为：" + serverSocket.getLocalPort() + "...");
                //侦听并接受此套接字的链接
                Socket server = serverSocket.accept();

                System.out.println("远程主机地址：" + server.getRemoteSocketAddress());
//               //返回此套接字的输入流
//                DataInputStream in=new DataInputStream(server.getInputStream());
//                in.readUTF();
                //返回此套接字的输出流,建立链接，读取套接字的输入流
                DataInputStream in=new java.io.DataInputStream(server.getInputStream());
                in.readUTF();

                DataOutputStream out=new DataOutputStream(server.getOutputStream());
                out.writeUTF("你已经链接到我了");




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
        }
    }
    public static void main(String [] args)
    {
        int port = Integer.parseInt(args[0]);
        try
        {
            Thread t = new Server(port);
            t.run();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}