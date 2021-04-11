package smilyk.atsarat.user.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Response {
    private Object content;
    private Integer code;
    private String timestamp;
}
