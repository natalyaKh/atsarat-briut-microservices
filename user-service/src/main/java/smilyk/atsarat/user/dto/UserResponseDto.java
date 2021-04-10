package smilyk.atsarat.user.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class UserResponseDto {
    String uuidUser;
    String mainEmail;
    String firstName;
    String secondName;
    String tz;
}
