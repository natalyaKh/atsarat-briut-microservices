package smilyk.atsarat.user.controllers;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smilyk.atsarat.user.dto.AddUserDto;
import smilyk.atsarat.user.dto.Response;
import smilyk.atsarat.user.service.users.UserService;
import smilyk.atsarat.user.service.validator.ValidateUserService;

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


    @PostMapping()
    public Response createUser(@RequestBody AddUserDto userDetails) throws Exception {
        Boolean validations = validatorService.checkUniqueEmail(userDetails.getMainEmail());
//        TODO create normal exception
        if(!validations) throw new Exception("gg");
        return userService.createUser(userDetails);
    }

}
