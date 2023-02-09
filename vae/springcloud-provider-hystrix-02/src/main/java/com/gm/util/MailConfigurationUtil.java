package com.gm.util;

import com.gm.vo.MailVo;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.*;
@Component
public class MailConfigurationUtil {
    @Autowired
    private MailUtil mailUtil;

    private final List<JavaMailSenderImpl> senderList;
    private MyMail myMail;

    public MyMail getMyMail() {
        return new MyMail();
    }

    public MailConfigurationUtil(List<JavaMailSenderImpl> senderList) {
        this.senderList = senderList;
    }


    /**
     * 获取邮箱发送者对象
     * @param mailName 邮箱源 （0=默认（qq),1=qq,2=163,null=随机)
     * @return 邮件发送封装类
     */
    public JavaMailSenderImpl getJavaMailSender(Integer mailName) throws Exception {
        //获取邮箱配置信息
        Map<String, MailUtil.MailProperties> mail = mailUtil.getMail();
        for (MailUtil.MailProperties mailProperties : mail.values()) {
            //创建邮箱发送者对象
            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

            //配置发送邮件的服务器主机地址
            javaMailSender.setHost(mailProperties.getHost());
            //配置授权码
            javaMailSender.setPassword(mailProperties.getPassword());
            //配置邮箱号
            javaMailSender.setUsername(mailProperties.getUsername());
            //配置端口
            javaMailSender.setPort(Integer.parseInt(mailProperties.getPort()));
            //配置协议
            javaMailSender.setProtocol(mailProperties.getProtocol());
            //配置编码
            javaMailSender.setDefaultEncoding(mailProperties.getEncoding());

            //8、创建连接对象，连接到邮箱服务器
            Properties properties = new Properties();
            //发送服务器需要身份验证,要采用指定用户名密码的方式去认证
            properties.put("mail.smtp.auth", true);//开启认证
            properties.put("mail.smtp.starttls.enable", true);
            properties.put("mail.smtp.starttls.required", true);
            properties.put("mail.smtp.ssl.enable", true);
            properties.put("mail.smtp.ssl.socketFactory.port", 465);//设置ssl端口
            properties.put("mail.smtp.ssl.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.debug", true);//启用调试
            //9、添加连接对象到邮件对象中
            javaMailSender.setJavaMailProperties(properties);
            // 添加数据
            senderList.add(javaMailSender);

        }
        if (senderList.isEmpty()) {
            throw new Exception("senderList不能为空");
        }
        if (mailName != null){
            return senderList.get(mailName);
        }
        // 随机返回一个JavaMailSender
        return senderList.get(new Random().nextInt(senderList.size()));
    }

    /**
     * 邮箱执行方法
     * @param from 发送者
     * @param to 接收者
     * @param toArray 接收群体
     * @param subject 主题
     * @param content 内容
     * @param cc 抄送
     * @param ccArray 抄送群体
     * @param bcc 密抄
     * @param bccArray 密抄群体
     * @return boolean 是否成功
     */
    private boolean getMail(Integer from, String to, String[] toArray, String subject, String content, String cc, String[] ccArray, String bcc, String[] bccArray) {
        try {
            JavaMailSenderImpl javaMailSender = getJavaMailSender(from);
            // 复杂类型消息对象
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            // 如果需要传递附件，必须在构造方法中传递一个true
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            MailVo mailVo = new MailVo();
            //发件人
            helper.setFrom(mailVo.getNickname()+"<"+Objects.requireNonNull(javaMailSender.getUsername())+">");
            //收件人
            if (to != null && !to.equals("")){
                helper.setTo(to);
            }else if (toArray != null && toArray.length > 0){
                helper.setTo(toArray);
            }
            //抄送
            if (cc != null && !cc.equals("")){
                helper.setCc(cc);
            }else if (ccArray != null && ccArray.length > 0){
                helper.setCc(ccArray);
            }
            //密抄
            if (bcc != null && !bcc.equals("")){
                helper.setBcc(bcc);
            }else if (bccArray != null && bccArray.length > 0){
                helper.setBcc(bccArray);
            }
            //主题
            if (subject != null && !subject.equals("")){
                helper.setSubject(subject);
            }

            //内容
            if (content != null && !content.equals("")){
                helper.setText(content,true);
            }
            // 设置发送的日期时间
            helper.setSentDate(new Date());
            // 发送
            javaMailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 邮箱相关信息--链式编程
     * set参数后，用go方法执行发送邮箱操作
     */
    @Data
    @Accessors(chain = true)
    public class MyMail{
        /**
         * 发送者 （0=默认（qq),1=qq,2=163,null=随机)
         */
        private Integer from;
        /**
         * 接收者
         */
        private String to;
        /**
         * 接收群体
         */
        private String[] toArray;
        /**
         * 主题
         */
        private String subject;
        /**
         * 内容--支持html
         */
        private String content;
        /**
         * 抄送
         */
        private String cc;
        /**
         * 抄送群体
         */
        private String[] ccArray;
        /**
         * 密抄
         */
        private String bcc;
        /**
         * 密抄群体
         */
        private String[] bccArray;

        /**
         * 发消息
         * @return boolean
         */
        public boolean go(){
            return MailConfigurationUtil.this.getMail(from,to,toArray,subject,content,cc,ccArray,bcc,bccArray);
        }
    }
}
