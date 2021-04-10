package smilyk.atsarat.user.service.users;

import org.springframework.stereotype.Service;
import smilyk.atsarat.user.dto.AddUserDto;
import smilyk.atsarat.user.dto.Response;

@Service
public interface UserService {
    Response createUser(AddUserDto userDetails);
}
