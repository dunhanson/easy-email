import org.junit.Test;
import site.dunhanson.email.entity.*;
import site.dunhanson.email.untils.EmailUtils;

public class Start {

    @Test
    public void text() {
        Smtp smtp = Smtp.builder()
                .hostName("smtp.163.com")
                .smtpPort(25)
                .ssl(true)
                .build();
        Authenticator authenticator = Authenticator.builder()
                .userName("dunhanson@163.com")
                .password("******")
                .build();
        Content content = Content.builder()
                .from("dunhanson@163.com")
                .subject("test-20200.425.1111")
                .msg("hello")
                .to("dunhanson@qq.com")
                .build();
        Result result = EmailUtils.sendText(smtp, authenticator, content);
        System.out.println(result);
    }

    @Test
    public void attachment() {
        Smtp smtp = Smtp.builder()
                .hostName("smtp.163.com")
                .smtpPort(25)
                .ssl(true)
                .build();
        Authenticator authenticator = Authenticator.builder()
                .userName("dunhanson@163.com")
                .password("******")
                .build();
        Content content = Content.builder()
                .from("dunhanson@163.com")
                .subject("winRAR windows 压缩包 - title")
                .msg("winRAR windows 压缩包 - content")
                .to("dunhanson@qq.com")
                .build();
        Attachment attachment = new Attachment();
        attachment.setDescription("winRAR windows 压缩包");
        attachment.setName("winrarchsyzcwggy.zip");
        attachment.setPathOrUrl("http://cr3.198254.com/winrarchsyzcwggy.zip");
        Result result = EmailUtils.sendAttachment(smtp, authenticator, content, attachment);
        System.out.println(result);
    }

    @Test
    public void html() {
        String htmlEmailTemplate = ".... <img src=\"http://www.apache.org/images/feather.gif\"> ....";
        Smtp smtp = Smtp.builder()
                .hostName("smtp.163.com")
                .smtpPort(25)
                .ssl(true)
                .build();
        Authenticator authenticator = Authenticator.builder()
                .userName("dunhanson@163.com")
                .password("******")
                .build();
        Content content = Content.builder()
                .from("dunhanson@163.com")
                .subject("html - title")
                .msg(htmlEmailTemplate)
                .to("dunhanson@qq.com")
                .build();
        Result result = EmailUtils.sendHTML(smtp, authenticator, content, "http://www.apache.org");
        System.out.println(result);
    }

}
