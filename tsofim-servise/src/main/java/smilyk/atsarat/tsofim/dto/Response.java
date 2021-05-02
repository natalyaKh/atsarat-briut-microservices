package smilyk.atsarat.tsofim.dto;

import lombok.*;
/**
 * Response object
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
