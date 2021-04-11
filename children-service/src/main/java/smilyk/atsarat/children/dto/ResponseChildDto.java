package smilyk.atsarat.children.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class ResponseChildDto {
    String uuidChild;
    String firstName;
    String secondName;
    //    TODO to code
    String uuidParent;
    String uuidRespPers;
    String tz;
}
