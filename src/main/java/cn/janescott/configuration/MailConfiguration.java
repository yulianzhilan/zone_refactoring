package cn.janescott.configuration;

import cn.janescott.common.constant.ConfigConstants;
import com.sun.mail.util.MailSSLSocketFactory;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * 由于对邮件配置信息进行了加密，所以，手动配置相对比较复杂
 * Created by scott on 2017/7/21.
 */
@Configuration
public class MailConfiguration {
    @Resource
    private Environment env;

    @Resource
    private StringEncryptor encryptor;

    @Bean
    public Session session() {
        Properties prop = new Properties();
        prop.setProperty(ConfigConstants.MAIL_PROTOCOL, env.getProperty(ConfigConstants.MAIL_PROTOCOL));
        prop.setProperty(ConfigConstants.MAIL_AUTH, env.getProperty(ConfigConstants.MAIL_AUTH));
        prop.setProperty(ConfigConstants.MAIL_HOST, encryptor.decrypt(env.getProperty(ConfigConstants.MAIL_HOST)));
        prop.setProperty(ConfigConstants.MAIL_PORT, encryptor.decrypt(env.getProperty(ConfigConstants.MAIL_PORT)));
        MailSSLSocketFactory sf;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put(ConfigConstants.MAIL_SOCKET_FACTORY, sf);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("创建邮件MailSSLSocketFactory失败", e);
        }

        prop.put(ConfigConstants.MAIL_SSL_ENABLE, env.getProperty(ConfigConstants.MAIL_SSL_ENABLE));
        return Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(encryptor.decrypt(env.getProperty(ConfigConstants.MAIL_USERNAME)), encryptor.decrypt(env.getProperty(ConfigConstants.MAIL_PASSWORD)));
            }
        });
    }

    @Bean
    public MimeMessage mimeMessage(Session session) {
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(encryptor.decrypt(env.getProperty(ConfigConstants.MAIL_USERNAME)), encryptor.decrypt(env.getProperty(ConfigConstants.MAIL_PERSON))));
            String to = encryptor.decrypt(env.getProperty(ConfigConstants.MAIL_TO));
            if (StringUtils.isEmpty(to)) {
                throw new RuntimeException("没有邮件接受者");
            }
            String[] acceptors = to.split(",");
            InternetAddress[] addresses = new InternetAddress[acceptors.length];
            for (int i = 0; i < acceptors.length; i++) {
                addresses[i] = new InternetAddress(acceptors[i]);
            }
            mimeMessage.setRecipients(Message.RecipientType.TO, addresses);
            mimeMessage.saveChanges();
//            mimeMessage.setSubject("异常监控");
//            mimeMessage.setText("默认内容");
//            Transport.send(mimeMessage);
        } catch (UnsupportedEncodingException | MessagingException e) {
            throw new RuntimeException("创建MimeMessage失败", e);
        }
        return mimeMessage;
    }

}