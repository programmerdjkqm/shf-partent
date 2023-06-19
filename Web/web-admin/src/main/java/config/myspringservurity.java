package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class myspringservurity extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//     auth.inMemoryAuthentication().withUser("root").
//             password(new BCryptPasswordEncoder().encode("123456"))
//             .roles("");
//    }
    /**
     * 必须指定加密方式，上下加密方式要一致
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //必须调用父类的方法，否则就不需要认证即可访问
//        super.configure(http);
        //允许iframe嵌套显示
        //http.headers().frameOptions().disable();
        //允许iframe显示
        http.headers().frameOptions().sameOrigin();
        http.authorizeRequests().antMatchers("/static/**","/login").permitAll()
                .anyRequest().authenticated();
        http.formLogin().loginPage("/login").defaultSuccessUrl("/");
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login");
        http.csrf().disable();
        http.exceptionHandling().accessDeniedHandler(new CustomAccessDeineHandler());
    }
}
