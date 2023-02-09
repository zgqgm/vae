package com.gm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gm.dao.UserChatMapper;
import com.gm.entity.UserChat;
import com.gm.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 猪猪侠
 * @since 2022-08-01
 */
@Service
public class ForumServiceImpl extends ServiceImpl<UserChatMapper, UserChat> implements ForumService {
    @Autowired
    private UserChatMapper userChatMapper;
    @Override
    public int addChat(HashMap<String, String> map) {
        System.out.println(map.toString());
        JSONObject jsonArray = new JSONObject();
        System.out.println(jsonArray);
        UserChat userChat = new UserChat();
        //userChat.setId(1);


        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //用户连接信息
            String url = "jdbc:mysql://localhost:3306/music?useSSL=false&characterEncoding=UTF8&useUnicode=true&serverTimeZone=Asia/Shanghai";
            String username = "root";
            String password = "958824";
            //连接数据库
            Connection connection = null;
            connection = DriverManager.getConnection(url,username,password);
            //创建数据库操作对象
            Statement statement = connection.createStatement();
            //执行sql语句，返回结果
            String sql = "select * from music.tb_userchat";
            ResultSet i = statement.executeQuery(sql);
                System.out.println(i.toString());

            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }




       // int insert = userChatMapper.insert(userChat);
        //System.out.println("插入是否成功"+insert+";\n是否回填id"+userChat.getId());
        return 0;
    }
}
