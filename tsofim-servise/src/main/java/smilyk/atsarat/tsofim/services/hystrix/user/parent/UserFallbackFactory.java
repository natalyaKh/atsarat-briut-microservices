package smilyk.atsarat.tsofim.services.hystrix.user.parent;


import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
class UserFallbackFactory implements FallbackFactory<UserServiceClient> {

	@Override
	public UserServiceClient create(Throwable cause) {
		return new UserServiceClientFallback(cause);
	}

}

