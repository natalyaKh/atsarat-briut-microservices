package smilyk.atsarat.user.service.users;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import smilyk.atsarat.user.dto.AddUserDto;
import smilyk.atsarat.user.dto.EmailVerificationDto;
import smilyk.atsarat.user.dto.Response;
import smilyk.atsarat.user.dto.UserResponseDto;
import smilyk.atsarat.user.enums.LoggerMessages;
import smilyk.atsarat.user.models.Users;
import smilyk.atsarat.user.repo.UserRepo;
import smilyk.atsarat.utils.UserUtils;
import smilyk.atsarat.utils.UuidUtils;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

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
}

