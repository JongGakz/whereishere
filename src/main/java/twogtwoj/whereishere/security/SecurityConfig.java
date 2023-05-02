package twogtwoj.whereishere.security;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.

                authorizeRequests()
                .antMatchers("/login").permitAll()
                // 접근 경로에 대한 권한들 설정
                // ** 기업 권한이필요한 페이지를 넣고 기업만 접근할 수 있게 하고, 개인 권한이 필요한 페이지를 넣고 개인만 접근할 수 있게 하자
                .anyRequest().authenticated()                            // 어떤 url로 접근하던 인증이 필요함을 선언

                .and()

                // 로그인 기능 모음
                .formLogin()
                .loginPage("/login")            // 사용자 정의 로그인 페이지로 이동 - 일단 default 입니다.
                .loginProcessingUrl("/login")
                .usernameParameter("loginId")
                .passwordParameter("loginPw")
                .failureUrl("/login?error=false") // 로그인이 실패하면 error의 파라미터가 false라는 값을 가지고, 로그인창으로 다시 보내줍니다.
                .defaultSuccessUrl("/main", true).permitAll() // 권한 승인
                .and()


                // 로그아웃 기능 모음
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // url 로그아웃으로 접근을 하게된다면, 로그아웃을 할 수 있다.
              ;


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/image/**","/js/**", "/login/signup/**");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }

}
