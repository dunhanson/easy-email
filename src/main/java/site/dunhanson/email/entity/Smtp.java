package site.dunhanson.email.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Smtp {
    private String hostName;
    private Integer smtpPort;
    private Boolean ssl;
}
