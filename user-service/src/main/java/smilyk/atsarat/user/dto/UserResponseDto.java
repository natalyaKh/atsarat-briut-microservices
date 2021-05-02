package smilyk.atsarat.user.dto;

import lombok.*;

/**
 * Dto for User Response object
 */
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
