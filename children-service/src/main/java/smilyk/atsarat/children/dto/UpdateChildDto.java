package smilyk.atsarat.children.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class UpdateChildDto {


    String firstName;
    String secondName;
    String tz;
    String uuidParent;
    String uuidRespPers;
}
