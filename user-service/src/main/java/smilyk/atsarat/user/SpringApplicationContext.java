package smilyk.atsarat.user;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * благодаря этому классу у UsersServiceImpl есть доступ к CONTEXT
 */
public class SpringApplicationContext implements ApplicationContextAware {
	private static ApplicationContext CONTEXT;

    @Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		CONTEXT = context;
	}

	public static Object getBean(String beanName) {
		return CONTEXT.getBean(beanName);
	}
}
