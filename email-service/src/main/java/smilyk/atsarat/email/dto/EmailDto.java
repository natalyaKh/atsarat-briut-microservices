package smilyk.atsarat.email.dto;




import lombok.*;
import smilyk.atsarat.email.enums.Services;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmailDto {

    String childFirstName;
    String childSecondName;
    String email;
    String picture;
    Services service;
    String lastName ;
    String firstName ;
}
