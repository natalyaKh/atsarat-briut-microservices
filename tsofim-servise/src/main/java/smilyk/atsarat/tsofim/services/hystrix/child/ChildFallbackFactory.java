package smilyk.atsarat.tsofim.services.hystrix.child;



import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
class ChildFallbackFactory implements FallbackFactory<ChildServiceClient> {

	@Override
	public ChildServiceClient create(Throwable cause) {
		return new ChildrenServiceClientFallback(cause);
	}

}

