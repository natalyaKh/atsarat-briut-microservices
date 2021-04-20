package smilyk.atsarat.tsofim.services.hystrix.user.respPerson;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RespPersonHystrixDto {

    String uuidRespPerson;
    String emailRespPerson;
    String firstName;
    String secondName;
    String tzRespPers;

}

