package site.dunhanson.email.untils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.*;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import site.dunhanson.email.entity.*;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class EmailUtils {

    /**
     * 发送文本
     * @param smtp
     * @param authenticator
     * @param content
     * @return
     */
    public static Result sendText(Smtp smtp, Authenticator authenticator, Content content) {
        Result result;
        try {
            Email email = new SimpleEmail();
            //设置smtp
            setSmtp(email, smtp);
            //设置认证
            setAuthenticator(email, authenticator);
            //设置内容
            setContent(email, content);
            //发送邮件
            String message = email.send();
            //返回结果
            result = new Result(true, message);
        } catch (EmailException e) {
            result = handleException(e.getCause());
        } catch (Exception e) {
            log.error(e.getMessage());
            result = handleException(e.getCause());
        }
        return result;
    }

    /**
     * 发送文件
     * @param smtp
     * @param authenticator
     * @param content
     * @param attachment
     * @return
     */
    public static Result sendAttachment(Smtp smtp, Authenticator authenticator, Content content, Attachment attachment) {
        Result result;
        try {
            //attachment
            EmailAttachment emailAttachment = new EmailAttachment();
            String pathOrUlr = attachment.getPathOrUrl();
            if(pathOrUlr.startsWith("http")) {
                emailAttachment.setURL(new URL(pathOrUlr));
            } else {
                emailAttachment.setPath(pathOrUlr);
            }
            emailAttachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription(attachment.getDescription());
            attachment.setName(attachment.getName());
            //email
            MultiPartEmail email = new MultiPartEmail();
            //设置smtp
            setSmtp(email, smtp);
            //设置认证
            setAuthenticator(email, authenticator);
            //设置内容
            setContent(email, content);
            //设置附件
            email.attach(emailAttachment);
            //发送邮件
            String message = email.send();
            //返回结果
            result = new Result(true, message);
        } catch (EmailException | MalformedURLException e) {
            result = handleException(e.getCause());
        } catch (Exception e) {
            log.error(e.getMessage());
            result = handleException(e.getCause());
        }
        return result;
    }

    /**
     * 发送邮件内容
     * @param smtp
     * @param authenticator
     * @param content
     * @return
     */
    public static Result sendHTML(Smtp smtp, Authenticator authenticator, Content content, String url) {
        Result result;
        try {
            //email
            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(new URL(url)));
            //设置smtp
            setSmtp(email, smtp);
            //设置认证
            setAuthenticator(email, authenticator);
            //设置内容
            setContent(email, content);
            //设置HTML
            email.setHtmlMsg(content.getMsg());
            //设置替换内容
            email.setTextMsg("Your email client does not support HTML messages");
            //发送邮件
            String message = email.send();
            //返回结果
            result = new Result(true, message);
        } catch (EmailException e) {
            result = handleException(e.getCause());
        } catch (Exception e) {
            log.error(e.getMessage());
            result = handleException(e.getCause());
        }
        return result;
    }

    /**
     * 设置smtp
     * @param email
     * @param smtp
     */
    public static void setSmtp(Email email, Smtp smtp) {
        email.setSmtpPort(smtp.getSmtpPort());
        email.setHostName(smtp.getHostName());
        email.setSSLOnConnect(smtp.getSsl());
    }

    /**
     * 设置认证
     * @param email
     * @param authenticator
     */
    public static void setAuthenticator(Email email, Authenticator authenticator) {
        email.setAuthenticator(new DefaultAuthenticator(authenticator.getUserName(), authenticator.getPassword()));
    }

    /**
     * 设置内容
     * @param email
     * @param content
     */
    public static void setContent(Email email, Content content) {
        try {
            email.addTo(content.getTo(), content.getToName());
            email.setFrom(content.getFrom(), content.getFromName());
            email.setSubject(content.getSubject());
            email.setMsg(content.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理异常信息
     * @param throwable
     * @return
     */
    public static Result handleException(Throwable throwable) {
        String clazz = throwable.getClass().getName();
        String error = throwable.getMessage();
        return new Result(false, clazz + ";" + error);
    }
}
