package smilyk.atsarat.tsofim.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * DTO for add tsofim details
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class AddTsofimDetailsDto {

    @NotNull(message = "uuidChild can not be null")
    private String uuidChild;

    @NotNull(message = "place can not be null")
    private String place;

    @NotNull(message = "groupTs can not be null")
    private String groupTs;

    @NotNull(message = "school can not be null")
    private String school;

    @NotNull(message = "childClass can not be null")
    private String childClass;

}
