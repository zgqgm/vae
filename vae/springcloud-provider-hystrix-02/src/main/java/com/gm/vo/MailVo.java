package com.gm.vo;

import lombok.Data;

@Data
public class MailVo {
    /**
     * 昵称
     */
    private String nickname = "Vae官方";
    /**
     *  发件人
     */
    private String from;
    /**
     *  收件人
     */
    private String[] to;
    /**
     * 抄送
     */
    private String[] cc;
    /**
     * 密抄
     */
    private String[] bcc;
    /**
     * 主题
     */
    private String subject;
    /**
     * 内容
     */
    private String content;
}
