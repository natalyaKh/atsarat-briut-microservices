package smilyk.atsarat.tsofim.dto;

import lombok.*;
/**
 * DTO for update tsofim details
 */
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
