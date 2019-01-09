package org.aminism.spider.huobi.common;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author ygxie
 * @date 2018/12/28.
 */
@Component
public class MailCaller {
    /**
     * 邮件头包含的内容有：
     *
     * from字段 　　--用于指明发件人
     * to字段 　　    --用于指明收件人
     * subject字段  --用于说明邮件主题
     * cc字段 　　   -- 抄送，将邮件发送给收件人的同时抄送给另一个收件人，收件人可以看到邮件抄送给了谁
     * bcc字段 　　 -- 密送，将邮件发送给收件人的同时将邮件秘密发送给另一个收件人，收件人无法看到邮件密送给了谁
     */
    public void sendMail(String content,String address) throws Exception{
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.126.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("yigangxie","mojing123");
            }
        });
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = session.getTransport();
        //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
        ts.connect("smtp.126.com", 465,"yigangxie", "xieyigang123");
        //4、创建邮件
        Message message = createSimpleMail(session,content,address);
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    private MimeMessage createSimpleMail(Session session,String content,String address) throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress("yigangxie@126.com"));
        //指明邮件的收件人"w952577382@126.com"
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(address));
        //邮件的标题
        message.setSubject("test");
        //邮件的文本内容
        message.setContent("content", "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        return message;
    }
}
