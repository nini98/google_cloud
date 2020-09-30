package web.project.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import web.project.backend.security.Http401ErrorEntryPoint;
import web.project.backend.security.jwt.JwtAuthFilter;
import web.project.backend.security.service.MyUserDetailsService;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private MyUserDetailsService myUserDetailsService;
	
	@Bean
    public Http401ErrorEntryPoint http401ErrorEntryPoint() {
        return new Http401ErrorEntryPoint();
    }

    @Bean
    public JwtAuthFilter tokenAuthenticationFilter() {
        return new JwtAuthFilter();
    }
    
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)
                .passwordEncoder(customPasswordEncoder());
    }
    
    @Bean
    public PasswordEncoder customPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(4));
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
            }
        };
    }
	
	
	public void addCorsMapping(CorsRegistry registry) {
		registry
				.addMapping("/**")
				.allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
				.allowedOrigins("http://localhost:3000")
				.allowedOrigins("http://localhost:8080");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
	        .and()
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .csrf()
	        .disable()
	        .formLogin()
	        .disable()
	        .httpBasic()
	        .disable()
	        .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
	        .exceptionHandling()
	        .authenticationEntryPoint(new Http401ErrorEntryPoint())
	        .and()
	        .authorizeRequests()
	        .antMatchers("/",
	                "/error",
	                "/favicon.ico",
	                "/**/*.png",
	                "/**/*.gif",
	                "/**/*.svg",
	                "/**/*.jpg",
	                "/**/*.jpeg",
	                "/**/*.html",
	                "/**/*.css",
	                "/**/*.js")
	        .permitAll()
            .antMatchers(HttpMethod.GET, "/blog/member/**").permitAll()
            .antMatchers(HttpMethod.GET, "/blog/**").permitAll()
            .antMatchers("/", "/error",
                    "/blog/Auth/signIn", "/blog/Auth/signUp",
                    "/blog/postwrite", //테스트를 위한 임시
                    "/v2/**", "/swagger-ui.html", "/swagger-resources/**")
            .permitAll()
            .anyRequest()
            .authenticated();
    }
	
}
