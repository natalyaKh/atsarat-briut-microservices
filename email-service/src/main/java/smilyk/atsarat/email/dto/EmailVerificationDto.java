package smilyk.atsarat.email.dto;

import lombok.*;

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
