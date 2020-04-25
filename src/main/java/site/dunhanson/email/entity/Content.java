package site.dunhanson.email.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Content {
    private String from;
    private String fromName;
    private String subject;
    private String msg;
    private String to;
    private String toName;
}
