package smilyk.atsarat.email.dto;

import lombok.*;
/**
 * message DTO Object for RabbitMQ
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class RabbitDto {
    private String uuidChild;
}
