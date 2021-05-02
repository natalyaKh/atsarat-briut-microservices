package smilyk.atsarat.user.dto;

import lombok.*;

/**
 * DTO for email-verification object
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
