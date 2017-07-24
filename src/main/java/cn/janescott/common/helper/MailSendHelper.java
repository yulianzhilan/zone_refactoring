package cn.janescott.common.helper;

import cn.janescott.common.constant.ConfigConstants;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * 发送邮件帮助类
 * Created by scott on 2017/7/21.
 */
@Component
public class MailSendHelper {
    @Resource
    private MimeMessage mimeMessage;

    /**
     * 发送文本邮件
     * @param subject
     * @param text
     */
    public void send(String subject, String text){
        try {
            mimeMessage.setSubject(subject);
            mimeMessage.setSentDate(new Date());
            mimeMessage.setText(text);
            mimeMessage.saveChanges();
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // todo 完善邮件帮助类

    /**
     * 发送网页邮件
     * @param subject
     * @param html
     */
    public void sendHtmlMail(String subject, String html){
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, ConfigConstants.DEFAULT_ENCODING);
            helper.setText(html, true);
            Transport.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送带有附件的邮件
     */
    public void sendAttendedFileMail(){

    }
}
