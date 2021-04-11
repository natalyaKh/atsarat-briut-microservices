package smilyk.atsarat.user.service.users;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import smilyk.atsarat.user.dto.*;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    Response createUser(AddUserDto userDetails);
    boolean verifyEmailToken(String token);

    //    need for addint in to token userUuid

    /**
     * need for creating token for user
     * @param email
     * @return {@link UserDto}
     */
    UserDto getUser(String email);

    UpdateUserDto updateUser(String id, UpdateUserDto userDetails);

    UserResponseDto getUserByUserId(String uuidUser);

    List<UserResponseDto> getUsers(int page, int limit);
}
