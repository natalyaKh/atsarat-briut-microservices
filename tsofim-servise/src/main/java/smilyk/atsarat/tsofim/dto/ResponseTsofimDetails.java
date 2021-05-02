package smilyk.atsarat.tsofim.dto;

import lombok.*;

/**
 * DTO for response tsofim details
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class ResponseTsofimDetails {
    private String uuidTsofimDetails;

    private String uuidChild;

    private String place;

    private String groupTs;

    private String school;

    private String childClass;
}
