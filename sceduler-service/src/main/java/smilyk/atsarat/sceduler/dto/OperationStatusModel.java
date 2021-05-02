package smilyk.atsarat.sceduler.dto;

import lombok.*;
/**
 * Operation Status info
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OperationStatusModel {
    private String operationResult;
    private String operationName;
}

