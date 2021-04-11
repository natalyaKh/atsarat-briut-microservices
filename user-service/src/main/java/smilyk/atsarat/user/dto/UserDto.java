package smilyk.atsarat.user.dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UserDto {
    private long id;
    private String uuidUser;
    private String firstName;
    private String secondName;
    private String mainEmail;
    private String altEmail;
    private String password;
    private String tz;
    private String confirmEmailToken;
    private Boolean deleted;
    private Boolean confirmEmail;
}
