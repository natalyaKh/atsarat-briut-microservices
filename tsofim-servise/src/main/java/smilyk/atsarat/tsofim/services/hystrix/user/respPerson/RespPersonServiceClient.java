package smilyk.atsarat.tsofim.services.hystrix.user.respPerson;



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

@FeignClient(name = "user-service", fallbackFactory = RespPersonFallbackFactory.class)
public interface RespPersonServiceClient {
    @GetMapping("/resp_pers/v1/{uuidRespPerson}}")
    @Headers("Authorization: {token}")
    public Response getResponsePersonByUserUuid(@PathVariable String uuidRespPerson,
                                                @RequestHeader("Authorization") String token);
//    );
    @Component
    class RespPersonFallbackFactory implements FallbackFactory<RespPersonServiceClient> {
        @Override
        public RespPersonServiceClient create(Throwable cause) {
            return new RespPersonServiceClientFallback(cause);
        }
    }

    class RespPersonServiceClientFallback implements RespPersonServiceClient {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        private final Throwable cause;

        public RespPersonServiceClientFallback(Throwable cause) {
            this.cause = cause;
        }

        @Override
        public Response getResponsePersonByUserUuid(String uuidRespPerson,
                                                   String req) {
//        ){
            if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
                logger.error("404 error took place when getResponsePersonByUserUuid was called with userId: " + uuidRespPerson + ". Error message: "
                        + cause.getLocalizedMessage());
            } else {
                logger.error("Other error took place: " + cause.getLocalizedMessage());
            }
            return new Response();
        }
    }
}
