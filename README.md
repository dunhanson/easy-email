# Easy Email

简化Apache Email操作

采取非过度封装策略，贴切原有Apache Email的SDK，即方便上手即用


## 简单开始

```java
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
```



## 配置文件

log4j.properties
```properties
### 设置###
log4j.rootLogger = info, stdout

### 输出信息到控制抬 ###
log4j.appender.stdout = org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target = System.out
log4j.appender.stdout.layout = org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern = [%-5p] %d{yyyy-MM-dd HH:mm:ss,SSS} method:%l%n%m%n
```

## Maven

`仅限公司内部项目配置生效`

```xml
<dependency>
    <groupId>site.dunhanson.email</groupId>
    <artifactId>easy-email</artifactId>
    <version>2020.0425.2130</version>
</dependency>
```

关联依赖
```xml
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-email</artifactId>
        <version>1.5</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.12</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>1.7.30</version>
    </dependency>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-log4j12</artifactId>
        <version>1.7.30</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## 参考资料

Apache Commons-Email

http://commons.apache.org/proper/commons-email/userguide.html

