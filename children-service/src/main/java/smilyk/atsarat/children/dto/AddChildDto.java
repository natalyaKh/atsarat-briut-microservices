package smilyk.atsarat.children.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO for add child info
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class AddChildDto {


    /**
     * The public first name of a user
     */
    @NotNull(message = "first name can not be null")
    @Size(min = 2, max = 50)
    String firstName;
    @NotNull(message = "first name can not be null")
    @Size(min = 2, max = 50)
    String secondName;
    //    TODO to code
    @NotNull(message = "tz can not be null")
    @Size(min = 9, max = 9)
    private String tz;

    String uuidParent;
    String uuidRespPers;
}
