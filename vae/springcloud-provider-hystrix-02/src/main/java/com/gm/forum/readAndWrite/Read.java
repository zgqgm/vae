package com.gm.forum.readAndWrite;

import java.io.BufferedReader;
import java.io.IOException;

public class Read implements Runnable{
    /*private InputStream inputStream;
    public Read(InputStream inputStream){
        this.inputStream = inputStream;
    }
    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(this.inputStream);
            while(true){
                System.out.println("读取消息：" + objectInputStream.readObject());
            }
            //闭流
            //objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/
    private BufferedReader bufferedReader;
    public Read(BufferedReader bufferedReader){
        this.bufferedReader = bufferedReader;
    }
    @Override
    public void run() {
        try {
            String str = null;
            while((str = bufferedReader.readLine()) != null){
                System.out.println("读取消息：" + str);
            }
            //闭流
            //objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
