package com.gm.forum.client;

import com.gm.forum.readAndWrite.Read;
import com.gm.forum.readAndWrite.Write;

import java.io.*;
import java.net.Socket;

public class ForumClient {
 /*   public static void main(String[] args) {
        //连接服务器
        try {
            System.out.println("连接服务端");
            Socket socket = new Socket("localhost",8181);
            System.out.println("连接服务端成功");
            Thread t1 = new Thread(new ClientWriteData(socket));
            Thread t2 = new Thread(new ClientReadData(socket));
            t1.start();
            t2.start();
        } catch (IOException e) {
            System.out.println("连接失败");
            e.printStackTrace();
        }
    }
}
class ClientWriteData implements Runnable{
    private Scanner scan;
    private Socket socket;
    PrintWriter pw = null;
    public ClientWriteData(Socket socket){
        this.socket = socket;
    }
    public void run(){
        try {
            //获得字节输出流
            OutputStream out = socket.getOutputStream();
            //解码
            OutputStreamWriter osw = new OutputStreamWriter(out,"GBK");
            //写入缓存区
            pw = new PrintWriter(osw,true);
            //后期改为数据库获取
            System.out.println("请输入用户名:");
            scan = new Scanner(System.in);
            String str0 = scan.next();
            //在流中打印名字
            pw.println(str0);
            while(true){
                //这里每次一循环就作为了一个聊天结束，不具有连续性----------------------------
                //后期改为数据库获取
                System.out.println("请选择私聊或者群发:1表示私聊\t2表示群发");
                int n = scan.nextInt();
                pw.println(n);
                switch(n){
                    case 1:
                        System.out.println("请输入私聊对象: ");
                        String name = scan.next();
                        pw.println(name);
                        System.out.println("私聊的信息:");
                        String str = scan.next();
                        pw.println(str);
                        break;
                    case 2:
                        System.out.println("请输入群发消息:");
                        System.out.println(str0+"正在发消息:");
                        String str1 = scan.next();
                        pw.println(str1);
                        break;
                    default:
                        System.out.println("退出消息发送:");
                        System.exit(0);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class ClientReadData implements Runnable{
    private Socket socket;
    public ClientReadData(Socket socket){
        this.socket = socket;
    }
    public void run(){
        try {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"GBK");
            BufferedReader br = new BufferedReader(isr);
            String str = null;
            while((str = br.readLine())!=null){
                System.out.println(str);
            }
        } catch (IOException e) {
            System.err.println("服务端已断开!!!");
            e.printStackTrace();
        }
    }*/
  /* public static void main(String[] args) {
       try {
           System.out.println("开始连接ip");
           Socket socket = new Socket("localhost",8181);
           System.out.println("ip连接完成，开始获取字节数据");
           InputStream inputStream = socket.getInputStream();
           OutputStream outputStream = socket.getOutputStream();
           System.out.println("获取字节数据完成,创建写线程和读线程");
           new Thread(new Write(outputStream)).start();
           new Thread(new Read(inputStream)).start();
           System.out.println("创建写线程和读线程完成,可以读写");
       } catch (IOException e) {
           e.printStackTrace();
       }
   }/*
   //客户端发消息
   class Client_Write implements Runnable{
       private OutputStream outputStream;
       public Client_Write(OutputStream outputStream){
           this.outputStream = outputStream;
       }

       @Override
       public void run() {
           Scanner sc = new Scanner(System.in);
           ObjectOutputStream outputStream = null;
           String str = null;
               try {
                   outputStream = new ObjectOutputStream(this.outputStream);

                   while (true){
                       JSONObject jsonObject = new JSONObject();
                       System.out.println("客户端发消息：");
                       str = sc.next();
                       jsonObject.put("客户端消息",str);
                       //发送消息
                       outputStream.writeObject(jsonObject);
                       //刷新缓存
                       outputStream.flush();
                   }
                   //后续添加结束聊天--闭流
                   //outputStream.close();
                   //sc.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }

       }
   }
    //读取服务端消息
    class Client_Reade implements Runnable{
        private InputStream inputStream;
        public Client_Reade(InputStream inputStream){
            this.inputStream = inputStream;
        }
        @Override
        public void run() {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(this.inputStream);
                while(true){
                    System.out.println("读取服务端消息：" + objectInputStream.readObject());
                }
                //闭流
                //objectInputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }*/
   /*public static void main(String[] args) {
       System.out.println("本机IP:" + getIpAddress());
   }

    public static String getIpAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("IP地址获取失败" + e.toString());
        }
        return "";
    }*/
 public static void main(String[] args) {
     try {
         Socket socket = new Socket("localhost", 8181);
         InputStream inputStream = socket.getInputStream();
         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
         OutputStream outputStream = socket.getOutputStream();
         PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream, "GBK"), true);
         new Thread(new Read(bufferedReader)).start();
         new Thread(new Write(printWriter)).start();
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
}
