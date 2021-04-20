package smilyk.atsarat.tsofim.services.hystrix.user.parent;



import feign.FeignException;
import feign.Headers;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import smilyk.atsarat.tsofim.dto.Response;

@FeignClient(name = "user-service", fallbackFactory = UserFallbackFactory.class)
public interface UserServiceClient {
    @GetMapping("/users/v1/{uuidUser}")
    @Headers("Authorization: {token}")
    public Response getUserByUserUuid(@PathVariable String uuidUser,
                                      @RequestHeader("Authorization") String token);
//    );
    @Component
    class UserFallbackFactory implements FallbackFactory<UserServiceClient> {
        @Override
        public UserServiceClient create(Throwable cause) {
            return new UserServiceClientFallback(cause);
        }
    }

    class UserServiceClientFallback implements UserServiceClient {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        private final Throwable cause;

        public UserServiceClientFallback(Throwable cause) {
            this.cause = cause;
        }

        @Override
        public Response getUserByUserUuid(String uuidUser,
                                                   String req) {
//        ){
            if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
                logger.error("404 error took place when getUserByUserUuid was called with userId: " + uuidUser + ". Error message: "
                        + cause.getLocalizedMessage());
            } else {
                logger.error("Other error took place: " + cause.getLocalizedMessage());
            }
            return new Response();
        }
    }
}
