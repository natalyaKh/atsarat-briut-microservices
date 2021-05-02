package smilyk.atsarat.user.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import smilyk.atsarat.user.constants.SecurityConstants;
import smilyk.atsarat.user.service.users.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@EnableWebSecurity
public class WebSecurityCofig extends WebSecurityConfigurerAdapter {
    private final UserService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public WebSecurityCofig(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Value("${gateway.port}")
    String managementPort;
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
         * needs for authentication with spring boot admin
         */
//        http.httpBasic();
        http
            /**adding cors*/
            .cors()
            .and().csrf().disable();
        http
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
            .permitAll()
            /**check e-mail - permittAll**/
            .antMatchers(HttpMethod.GET, SecurityConstants.VERIFICATION_EMAIL_URL)
            .permitAll()
            /**changePassword - доступно всем**/
            .antMatchers(HttpMethod.POST, SecurityConstants.PASSWORD_RESET_REQUEST_URL)
            .permitAll()
            .antMatchers("/monitor/health").permitAll()
//            .antMatchers("/monitor/**").hasAuthority("ENDPOINT_ADMIN")
            .antMatchers("/v2/api-docs/**").permitAll()
            .antMatchers("/swagger.json").permitAll()
            .antMatchers("/swagger-ui.html").permitAll()
            .antMatchers("/swagger-resources/**").permitAll()
            .antMatchers("/webjars/**").permitAll()
            .and()
            /**
             * filter for all object with authenticated

             */
            .addFilter(getAuthenticationFilter())
            /**
             * only authonticated user can do something in app
             */
            .addFilter(new AuthorizationFilter(authenticationManager()));
        /**
         * delete cash of token
         */
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    /**
     * login user now by link .../users/login
     */
    protected AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/users/login");
        return filter;
    }

    /**
     * add cors
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("*"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
