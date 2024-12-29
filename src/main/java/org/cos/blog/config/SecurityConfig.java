package org.cos.blog.config;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.cos.blog.auth.PrincipalDetail;
import org.cos.blog.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration // 빈등록(IoC)
@EnableWebSecurity // 시큐리티 필터가 등록이 된다.
@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻 : 현재는 사용x
public class SecurityConfig {

    private final PrincipalDetailService principalDetailService;

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // 시큐리티가 대신 로그인해주는데 passsword를 가로채기를 하는데
    // 해당 password가 무엇으로 해쉬가 되어 회원가입이 되었는지 알아야
    // 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable) // 시큐리티 로그인폼 사용 x
                .csrf(AbstractHttpConfigurer::disable) // csrf 토큰 비활성화(테스트시 걸어두는 게 좋음)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((req) -> req
                        .requestMatchers("/","/auth/**", "/js/**", "/css/**", "/image/**", "/WEB-INF/views/**") // auth로 들어오는 건 누구나 접속 가능
                        .permitAll()
                        .anyRequest()// auth가 아닌 다른 request들은
                        .authenticated()) // 인증이 되어야 한다
                .formLogin(formLogin -> formLogin
                        .loginPage("/auth/loginForm") // 로그인 페이지 설정 -> 인증이 되지 않은 페이지에 대한 요청은 해당 페이지로 온다.
                        .permitAll()
                        .loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다.
                        .defaultSuccessUrl("/"))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/loginForm"));

        return http.build();
    }
}