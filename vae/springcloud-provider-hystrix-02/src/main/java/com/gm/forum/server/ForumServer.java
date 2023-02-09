package com.gm.forum.server;

import com.gm.forum.readAndWrite.Read;
import com.gm.forum.readAndWrite.Write;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForumServer {
    //群发
    //定义一个集合,用于存储所有客户端的输出流
    List<PrintWriter> allPw = new ArrayList<>();
    //用于根据用户名存储客户端输出流
    Map<String,PrintWriter> map = new HashMap();
    //创建线程池对象
    //ExecutorService pool = Executors.newCachedThreadPool();
   /* public static void main(String[] args) {
       // for (int p = 1024; p < 65535; p++){
            try {
                //向系统申请端口号 端口号是系统中所有程序没有使用过的端口,才能使用成功
                //端口号的范围:0-65535之间 0-1023之间是系统端口
                ServerSocket server = new ServerSocket(8181);//启动服务端 port设置0时随机分配端口
                System.out.println("服务端开启成功!!!");
                //实例化ForumServer
                ForumServer s = new ForumServer();
                while(true){//死循环，目的：不断地去接收客户端的请求
                    System.out.println("等待客户端连接:");
                    //阻塞方法 accept():等待客户端的连接
                    Socket accept = server.accept();//当客户端有请求时，线程执行。反之，线程阻塞。
                    System.out.println("和客户端连接成功!!!");
                    //创建InetAddress对象
                    InetAddress address = accept.getInetAddress();
                    //获取连接客户端IP地址
                    String ip = address.getHostAddress();
                    System.out.println(ip);

                    Thread t = new Thread(s.new ClientHandler(accept));
                    t.start();
                }

            } catch (IOException e) {
                System.out.println("端口"+"绑定失败");
                e.printStackTrace();
            }
        //}
    }
    //监听客户端
    class ClientHandler implements Runnable{
        private Socket socket;
        public ClientHandler(Socket socket){
            this.socket = socket;
        }
        public void run(){
            try{
                //获取客户端的字节输入流
                //InputStream is = socket.getInputStream();
                //解码字节
                //InputStreamReader isr = new InputStreamReader(is,"GBK");
                //转为字符输入流
                //BufferedReader br = new BufferedReader(isr);

                //获取客户端的字节输出流
                OutputStream os = socket.getOutputStream();
                //解码字节
                OutputStreamWriter osw = new OutputStreamWriter(os,"GBK");
                //将字节输出流写入缓存区
                PrintWriter pw = new PrintWriter(osw,true);

                //一个客户端连接服务器,则把输出流存入到集合中
                String str = null;
                //用户名
                //String name = br.readLine();//等待对方输入
                //添加流
                allPw.add(pw);
                //map.put(name, pw);
                System.out.println(name);
                while(name!=null){
                    str = br.readLine();
                    System.out.println(str);
                    if(str.equals("1")){//私聊
                        //需要私聊的对象
                        //str = br.readLine();
                        //私聊对象的输出流
                        PrintWriter p = map.get(name);
                        str = br.readLine();
                        if (!str.equals("")||str!=null){
                            p.println(str);
                        }

                    }else{//群发

                        System.out.println("");
                        str = br.readLine();
                        for(PrintWriter p:allPw){
                            if(p == pw){
                                continue;
                            }
                            p.println(str);
                        }
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }*/

    /*public static void main(String[] args) {
        try {
            System.out.println("开始绑定ip");
            ServerSocket serverSocket = new ServerSocket(8181);
            System.out.println("ip绑定完成，等待连接");
            //每循环一次为连接一个ip，没有客户端请求连接时，阻塞循环，进入等待
            while (true){
               // 同一个socket，如果调用两次会报StreamCorruptedException，所以不要乱传socket
               Socket accept = serverSocket.accept();
               System.out.println("连接完成,开始获取字节数据");
               InputStream inputStream = accept.getInputStream();
               OutputStream outputStream = accept.getOutputStream();
               System.out.println("获取字节数据完成,创建写线程和读线程");
               new Thread(new Write(outputStream)).start();
               new Thread(new Read(inputStream)).start();
               System.out.println("创建写线程和读线程完成,可以读写");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }/*
    //监听客户端--读取客户端输入的信息
    class Client_listen implements Runnable{
        private InputStream inputStream;
        public Client_listen(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream oIS = new ObjectInputStream(this.inputStream);
                while(true){
                    System.out.println("读取客户端消息：" + oIS.readObject());
                }
                //闭流
                //oIS.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //服务端发消息
    class Server_sent implements Runnable{
        private OutputStream outputStream;
        public Server_sent(OutputStream outputStream) {
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
                        //内部是用Hashmap来存储的，无序不可重复
                        //想要有序可改为LinkedHashMap ：newJSONObject(new LinkedHashMap())
                        JSONObject jsonObject = new JSONObject();
                        System.out.println("服务端发消息：");
                        str = sc.next();
                        jsonObject.put("服务端消息",str);
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
    }*/
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8181);
            while(true){
                Socket accept = serverSocket.accept();
                InputStream inputStream = accept.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
                OutputStream outputStream = accept.getOutputStream();
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream, "GBK"), true);
                new Thread(new Read(bufferedReader)).start();
                new Thread(new Write(printWriter)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
