package smilyk.atsarat.tsofim.services.hystrix.user.parent;



import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import smilyk.atsarat.tsofim.dto.Response;

class UserServiceClientFallback implements UserServiceClient {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private final Throwable cause;

	public UserServiceClientFallback(Throwable cause) {
		this.cause = cause;
	}


//
//Need if there is token
 	@Override
	public Response getUserByUserUuid(String childUuid, String req) {
		if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
			logger.error("404 error took place when getUserByUserUuid was called with childUuid: " + childUuid + ". Error message: "
					+ cause.getLocalizedMessage());
		} else {
			logger.error("Other error took place: " + cause.getLocalizedMessage());
		}
		return new Response();
	}

//	@Override
//	public Response getUserByUserUuid(String uuidChild) {
//		if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
//			logger.error("404 error took place when getAllChildren was called with childUuid: " + uuidChild + ". Error message: "
//					+ cause.getLocalizedMessage());
//		} else {
//			logger.error("Other error took place: " + cause.getLocalizedMessage());
//		}
//		return new Response();
//	}
}
