package smilyk.atsarat.email.dto;

import lombok.*;
/**
 * DTO for email  verification Object
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmailVerificationDto {
    String tokenValue;
    String userName;
    String userLastName;
    String email;
}
