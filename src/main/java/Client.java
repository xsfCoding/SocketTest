


import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.net.*;
import java.io.*;

public class Client
{
    public static void main(String [] args)
    {
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        try
        {
            System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
            Socket client = new Socket(serverName, port);
            System.out.println("远程主机地址：" + client.getRemoteSocketAddress());


            DataOutputStream out=new DataOutputStream(client.getOutputStream());
            out.writeUTF("hello,server,我来发送链接了");

            DataInputStream in=new DataInputStream(client.getInputStream());

            System.out.println("得到服务器的相应"+in.readUTF());

            client.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}