package smilyk.atsarat.sceduler.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class AddPlanDto {
    @NotNull(message = "uuidChild can not be null")
    private String uuidChild;
    @NotNull(message = "service can not be null")
    private String service;
    private Boolean sendEmail = true;
    private LocalDateTime date;
    private Boolean monday;
//    private Double monday_time;
    private Boolean tuesday;
//    private Double tuesday_time;
    private Boolean wednesday;
//    private Double wednesday_time;
    private Boolean thursday;
//    private Double thursday_time;
    private Boolean friday;
//    private Double friday_time;
    private Boolean saturday;
//    private Double saturday_time;
    private Boolean sunday;
//    private Double sunday_time;
}
