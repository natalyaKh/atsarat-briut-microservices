package smilyk.atsarat.user.controllers;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smilyk.atsarat.user.dto.AddUserDto;
import smilyk.atsarat.user.dto.OperationStatusModel;
import smilyk.atsarat.user.dto.Response;
import smilyk.atsarat.user.enums.RequestOperationName;
import smilyk.atsarat.user.enums.RequestOperationStatus;
import smilyk.atsarat.user.service.users.UserService;
import smilyk.atsarat.user.service.validator.ValidateUserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/users/v1")
public class UserController {
    private String currentDate = LocalDateTime.now().toLocalDate().toString();
    @Autowired
    ValidateUserService validatorService;

    @Autowired
    UserService userService;


    /**
     * create {@link smilyk.atsarat.user.models.Users} user and save it to DB
     * @param userDetails
     * @return
     * @throws Exception
     */
    @PostMapping()
    public Response createUser(@RequestBody AddUserDto userDetails) throws Exception {
        Boolean validations = validatorService.checkUniqueEmail(userDetails.getMainEmail());
//        TODO create normal exception
        if(!validations) throw new Exception("gg");
        return userService.createUser(userDetails);
    }

    /**
     *
     * email-verification - confirm-email
     * http://localhost:8080/users/email-verification?token=sdfsdf
     * */
    @GetMapping(path = "/email-verification")
    public Response verifyEmailToken(@RequestParam(value = "token") String token) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.VERIFY_EMAIL.name());
        boolean isVerified = userService.verifyEmailToken(token);
        if (isVerified) {
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        } else {
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
        }

        return new Response(returnValue, HttpServletResponse.SC_OK, currentDate);
    }

}
