package smilyk.atsarat.children.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Environment environment;
    @Value("${gateway.port}")
    String managementPort;

    @Autowired
    public WebSecurityConfig(Environment environment) {
        this.environment = environment;
    }

    /**
     * permitAll for zuul-gateway-service
     */
//    private int managementPort = Integer.parseInt(environment.getProperty("gateway.port"));

    @Override
    public void configure(WebSecurity web) throws Exception {
        int managerPortInt = Integer.parseInt(managementPort);
        web.ignoring().requestMatchers(forPort(managerPortInt));
    }

    private RequestMatcher forPort(int port) {
        return (HttpServletRequest request) -> {
            return port == request.getLocalPort();
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * needs for autentication with spring boot admin
         */
        http.httpBasic();
        /**добавляет cors*/
        http.cors()
            .and().csrf().disable();
        http.authorizeRequests()
            .antMatchers("/monitor/health").permitAll()
            .antMatchers("/v2/api-docs/**").permitAll()
            .antMatchers("/swagger.json").permitAll()
            .antMatchers("/swagger-ui.html").permitAll()
            .antMatchers("/swagger-resources/**").permitAll()
            .antMatchers("/webjars/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(new AuthorizationFilter(authenticationManager(), environment));
        http.headers().frameOptions().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
