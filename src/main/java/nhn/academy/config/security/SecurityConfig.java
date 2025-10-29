package nhn.academy.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/private-project/**").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers("/public-project/**").permitAll()  // JSESSIONID 쿠키가 안만들어짐 ㄷㄷ
                                .anyRequest().authenticated()  // 모든 request는 인증이 되어있어야함!
        );
        // csrf diable
        http.csrf(AbstractHttpConfigurer::disable);
        // UsernamePasswordAuthenticationFilter가 활성화
        http.formLogin(Customizer.withDefaults());  // 여기에 숨어있는게 .requestMatchers("/login").permitAll() <- 그래서 JSESSIONID 쿠키가 안만들어짐
        return http.build();
    }

    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler(RoleHierarchy roleHierarchy) {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy);
        return expressionHandler;
    }


    @Bean
    public RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.fromHierarchy("ROLE_ADMIN > ROLE_MEMBER"); //// ROLE_ADMIN은 ROLE_MEMBER보다 상위
    }


    // CustomUserDetailService로 바꿔보자
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin")
//                .roles("ADMIN")
//                .build();
//
//        UserDetails member = User.withDefaultPasswordEncoder()
//                .username("member")
//                .password("member")
//                .roles("MEMBER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, member);
//    }
}