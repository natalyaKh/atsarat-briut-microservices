package smilyk.atsarat.children.dto;

import lombok.*;

/**
 * Response object info
 */
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
