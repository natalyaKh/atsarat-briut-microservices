package smilyk.atsarat.user.service.users;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import smilyk.atsarat.user.dto.*;

import java.util.List;


import smilyk.atsarat.user.dto.*;

import java.util.List;


import smilyk.atsarat.user.dto.*;

import java.util.List;


import smilyk.atsarat.user.dto.*;

import smilyk.atsarat.user.dto.AddUserDto;
import smilyk.atsarat.user.dto.Response;
import smilyk.atsarat.user.dto.UpdateUserDto;

import smilyk.atsarat.user.dto.UserDto;
import smilyk.atsarat.user.models.Users;


/**
 * {@link Service} for handling {@link Users} entity.
 */
@Service
public interface UserService extends UserDetailsService {
    /**
     * save provided {@link Users} entity
     * @param userDetails {@link AddUserDto}
     * @return {@link Response} object with {@link UserResponseDto}
     */
    Response createUser(AddUserDto userDetails);
    /**
     * verification token in email-confirmation
     * @param token
     * @return boolean
     */
    boolean verifyEmailToken(String token);
    /**
     * Find {@link Users} by uuidChild.
     * @param email of provided {@link  Users}
     * @return  {@link UserDto} with provided user UUID or null otherwise.
     */
    UserDto getUser(String email);
    /**
     * Update provided {@link Users} by uuid of user
     * @param id of provided {@link Users} by uuid of user
     * @param userDetails of provided {@link UpdateUserDto}
     * @return updated {@link UpdateUserDto} object
     */
    UpdateUserDto updateUser(String id, UpdateUserDto userDetails);
    /**
     * Find {@link Users} by uuidChild.
     * @param uuidUser of provided {@link  Users}
     * @return  {@link UserResponseDto} with provided user UUID or null otherwise.
     */
    UserResponseDto getUserByUserId(String uuidUser);

    /**
     * delete provided {@link Users} entity by uuidUser
     * @param id of provided {@link Users}
     * @return boolean
     */
    Boolean deleteUser(String id);
    /**
     *  Retrieve all {@link Users}
     * @param page, default = 0
     * @param limit, default = 2
     * @return the collection of the {@link UserResponseDto} objects
     */
    List<UserResponseDto> getUsers(int page, int limit);

}
