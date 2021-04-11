package smilyk.atsarat.user.service.users;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import smilyk.atsarat.user.dto.*;
import smilyk.atsarat.user.enums.ErrorMessages;
import smilyk.atsarat.user.enums.LoggerMessages;
import smilyk.atsarat.user.models.Users;
import smilyk.atsarat.user.repo.UserRepo;
import smilyk.atsarat.utils.UserUtils;
import smilyk.atsarat.utils.UuidUtils;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

import java.util.ArrayList;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private String currentDate = LocalDateTime.now().toLocalDate().toString();
    ModelMapper modelMapper = new ModelMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UuidUtils uuidUtils;
    @Autowired
    UserUtils utils;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepo userRepo;
    @Autowired
    AmqpTemplate rabbitTemplate;

    @Value(("${email.exchange}"))
    String emailExchange;
    @Value(("${email.key}"))
    String emailRoutingkey;


    @Override
    public Response createUser(AddUserDto userDetails) {
        Users userEntity = modelMapper.map(userDetails, Users.class);
        userEntity.setUuidUser(uuidUtils.generateUuid().toString());
        userEntity.setConfirmEmailToken(null);
        userEntity.setPassword(bCryptPasswordEncoder.encode(userDetails.getNotDecryptedPassword()));
        userEntity.setConfirmEmailToken(utils.generateEmailVerificationToken(utils.generateEmailVerificationToken(
            uuidUtils.generateUuid().toString())));
        UserResponseDto userResponseDto = modelMapper.map(userEntity, UserResponseDto.class);
        userRepo.save(userEntity);
        EmailVerificationDto emailDto = EmailVerificationDto.builder()
            .email(userEntity.getMainEmail())
            .tokenValue(userEntity.getConfirmEmailToken())
            .userLastName(userEntity.getSecondName())
            .userName(userEntity.getFirstName())
            .build();
//        method - send email
        rabbitTemplate.convertAndSend(emailExchange, emailRoutingkey, emailDto);
        LOGGER.info("verification email was send to email " + userResponseDto.getMainEmail());
        userRepo.save(userEntity);
        LOGGER.info(LoggerMessages.ADD_USER + ' ' + userEntity.getMainEmail());
        LOGGER.info(LoggerMessages.ADD_USER + ' ' + userEntity.getMainEmail());
        return new Response(userResponseDto, HttpServletResponse.SC_CREATED, currentDate);
    }

    @Override
    public boolean verifyEmailToken(String token) {
        boolean rez = false;
        // Find user by token
        Optional<Users> optionalUsersEntity = userRepo.findUserByConfirmEmailToken(token);
//            if there is token - that means - not confirmed
        if (optionalUsersEntity.isPresent()) {
//                check time of token
            boolean hastokenExpired = utils.hasTokenExpired(token);
            Users userEntity = optionalUsersEntity.get();
            if (!hastokenExpired) {
                userEntity.setConfirmEmailToken(null);
                userEntity.setConfirmEmail(Boolean.TRUE);
                userRepo.save(userEntity);
                LOGGER.info(LoggerMessages.USER_CONFIRM_EMAIL + userEntity.getMainEmail());
                rez = true;
            }
        }
        return rez;
    }


    /**
     * method that return user for authentication
     * @param email
     * @return UserDto
     */
    @Override
    public UserDto getUser(String email) {
        Optional<Users> optionalUser = userRepo.findByMainEmail(email);
        if (!optionalUser.isPresent())
            throw new UsernameNotFoundException(email);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(optionalUser.get(), returnValue);
        return returnValue;
    }

    @Override
    public UpdateUserDto updateUser(String uuidUser, UpdateUserDto user) {
        Optional<Users> optionalUserEntity = userRepo.findByUuidUserAndDeleted(uuidUser, false);
        if (!optionalUserEntity.isPresent()) {
            LOGGER.error(LoggerMessages.USER_WITH_UUID + uuidUser + LoggerMessages.NOT_FOUND);
            throw new UsernameNotFoundException(
                ErrorMessages.USER_WITH_UUID + uuidUser + ErrorMessages.NOT_FOUND);
        }
        LOGGER.info(LoggerMessages.USER_WITH_UUID + uuidUser + LoggerMessages.WAS_RETURND);
        Users userEntity = optionalUserEntity.get();

        if (user.getFirstName() != null) {
            userEntity.setFirstName(user.getFirstName());
            LOGGER.info(LoggerMessages.USER_WITH_UUID + uuidUser + LoggerMessages.CHANGE +
                LoggerMessages.FIRST_NAME + user.getFirstName());
        }
        if (user.getSecondName() != null) {
            userEntity.setSecondName(user.getSecondName());
            LOGGER.info(LoggerMessages.USER_WITH_UUID + uuidUser + LoggerMessages.CHANGE +
                LoggerMessages.SECOND_NAME + user.getSecondName());
        }
        if (user.getTz() != null) {
            userEntity.setTz(user.getTz());
//            TODO зашифровать ТЗ
            LOGGER.info(LoggerMessages.USER_WITH_UUID + uuidUser + LoggerMessages.CHANGE +
                LoggerMessages.TZ + user.getTz());
        }
        userRepo.save(userEntity);
        LOGGER.info((LoggerMessages.USER_WITH_UUID + uuidUser + LoggerMessages.WAS_UPDATE));
        return modelMapper.map(userEntity, UpdateUserDto.class);
    }

    @Override
    public UserResponseDto getUserByUserId(String uuidUser) {
        Optional<Users> optionalUserEntity = userRepo.findByUuidUserAndDeleted(uuidUser, false);
        if (!optionalUserEntity.isPresent()) {
            LOGGER.error(LoggerMessages.USER_WITH_UUID + uuidUser + LoggerMessages.NOT_FOUND);
            throw new UsernameNotFoundException(
                ErrorMessages.USER_WITH_UUID + uuidUser + ErrorMessages.NOT_FOUND);
        }
        LOGGER.info(LoggerMessages.USER_WITH_UUID + uuidUser + LoggerMessages.WAS_RETURND);
        return modelMapper.map(optionalUserEntity.get(), UserResponseDto.class);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> optionalUser = userRepo.findByMainEmail(email);
        if (!optionalUser.isPresent())
            throw new UsernameNotFoundException(email);
        /**
         * if optionalUser.get().getConfirmEmail(), == false -> login return error
         */
        return new User(optionalUser.get().getMainEmail(),
            optionalUser.get().getPassword(),
            optionalUser.get().getConfirmEmail(),
            true, true, true, new ArrayList<>());
    }


}

