package smilyk.atsarat.user.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class AddUserDto {
    @NotNull(message = "e-mail can not be null")
    @Email(message = "you should put valid e-mail")
    String mainEmail;
    @NotNull(message = "password can not be null")
    @Size(min=8, max=50)
    String notDecryptedPassword;
    @NotNull(message = "first name can not be null")
    @Size(min=2, max=50)
    String firstName;
}
