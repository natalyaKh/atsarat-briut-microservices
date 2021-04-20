package smilyk.atsarat.tsofim.services.hystrix.child;



import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import smilyk.atsarat.tsofim.dto.Response;

class ChildrenServiceClientFallback implements ChildServiceClient {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private final Throwable cause;

	public ChildrenServiceClientFallback(Throwable cause) {
		this.cause = cause;
	}


//
//Need if there is token
 	@Override
	public Response getChildByChildUuid(String childUuid, String req) {
		if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
			logger.error("404 error took place when getAllChildren was called with childUuid: " + childUuid + ". Error message: "
					+ cause.getLocalizedMessage());
		} else {
			logger.error("Other error took place: " + cause.getLocalizedMessage());
		}
		return new Response();
	}

//	@Override
//	public Response getChildByChildUuid(String uuidChild) {
//		if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
//			logger.error("404 error took place when getAllChildren was called with childUuid: " + uuidChild + ". Error message: "
//					+ cause.getLocalizedMessage());
//		} else {
//			logger.error("Other error took place: " + cause.getLocalizedMessage());
//		}
//		return new Response();
//	}
}
