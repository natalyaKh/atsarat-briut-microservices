package smilyk.atsarat.tsofim.dto;

import lombok.*;
/**
 * DTO for email
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class EmailDto {
    String email ;
    String picture ;
    String lastName ;
    String firstName ;
    String childFirstName ;
    String childSecondName ;
    String service;
}
