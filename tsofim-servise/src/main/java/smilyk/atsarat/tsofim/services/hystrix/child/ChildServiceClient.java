package smilyk.atsarat.tsofim.services.hystrix.child;



import feign.FeignException;
import feign.Headers;
import feign.hystrix.FallbackFactory;
import org.apache.tomcat.util.http.parser.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import smilyk.atsarat.tsofim.dto.Response;

@FeignClient(name = "children-service", fallbackFactory = ChildFallbackFactory.class)
public interface ChildServiceClient {
    @GetMapping("/child/v1/{uuidChild}")
    @Headers("Authorization: {token}")
    public Response getChildByChildUuid(@PathVariable String uuidChild,
                                        @RequestHeader("Authorization") String token);
//    );
    @Component
    class ChildFallbackFactory implements FallbackFactory<ChildServiceClient> {
        @Override
        public ChildServiceClient create(Throwable cause) {
            return new ChildrenServiceClientFallback(cause);
        }
    }

    class ChildrenServiceClientFallback implements ChildServiceClient {

        Logger logger = LoggerFactory.getLogger(this.getClass());

        private final Throwable cause;

        public ChildrenServiceClientFallback(Throwable cause) {
            this.cause = cause;
        }

        @Override
        public Response getChildByChildUuid(String childUuid,
                                                   String req) {
//        ){
            if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
                logger.error("404 error took place when getChildByChildUuid was called with userId: " + childUuid + ". Error message: "
                        + cause.getLocalizedMessage());
            } else {
                logger.error("Other error took place: " + cause.getLocalizedMessage());
            }
            return new Response();
        }
    }
}
