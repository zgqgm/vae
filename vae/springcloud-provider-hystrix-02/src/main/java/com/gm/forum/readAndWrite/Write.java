package com.gm.forum.readAndWrite;

import java.io.PrintWriter;
import java.util.Scanner;

public class Write implements Runnable{
    /*private OutputStream outputStream;
    public Write(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        ObjectOutputStream objectOutputStream = null;
        String str = null;
        try {
            objectOutputStream = new ObjectOutputStream(this.outputStream);

            while (true){
                //JSONObject jsonObject = new JSONObject();
                HashMap<String, Object> jsonObject = new HashMap<>();
                System.out.println("发消息：");
                str = sc.next();
                jsonObject.put("消息",str);
                //发送消息
                objectOutputStream.writeObject(jsonObject);
                //刷新缓存
                objectOutputStream.flush();
            }
            //后续添加结束聊天--闭流
            //outputStream.close();
            //sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/
    private PrintWriter printWriter;
    public Write(PrintWriter printWriter){
        this.printWriter = printWriter;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        String str = null;
        while (true){
            System.out.println("发消息：");
            str = sc.next();
            printWriter.println(str);
        }
        //后续添加结束聊天--闭流
        //printWriter.close();
        //sc.close();

    }
}
