package smilyk.atsarat.tsofim.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class UpdateTsofimDetailDto {

    private String uuidChild;

    private String place;

    private String groupTs;

    private String school;

    private String childClass;
}
