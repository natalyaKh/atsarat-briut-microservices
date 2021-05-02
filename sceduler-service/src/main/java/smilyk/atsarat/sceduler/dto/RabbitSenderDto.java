package smilyk.atsarat.sceduler.dto;

import lombok.*;
/**
 * Response for RabbitMQ  for sending messages to services
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class RabbitSenderDto {
    private String uuidChild;
}
