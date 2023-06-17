package cn.pqz.emsboot.config.security;
import cn.pqz.emsboot.component.security.*;
import cn.pqz.emsboot.component.security.smscode.SmsCodeSecurityConfig;
import cn.pqz.emsboot.component.util.UserUtil;
import cn.pqz.emsboot.modules.sys.entity.RespBean;
import cn.pqz.emsboot.modules.sys.entity.User;
import cn.pqz.emsboot.modules.sys.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;
    @Autowired
    CustomAccessDecisionManager accessDecisionManager;
    @Autowired
    CustomFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;
    @Autowired
    AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;
    @Autowired
    VerificationCodeFilter ver;//验证码拦截器
    @Autowired
    MyExpiredSessionStrategy myExpiredSessionStrategy;//session过期处理
    @Autowired
    MyLogoutSuccessHandler myLogoutSuccessHandler;//注销登录成功处理
    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;//登录成功处理
    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;//登录失败处理
    @Autowired
    SmsCodeSecurityConfig smsCodeSecurityConfig;
    @Resource
    DataSource dataSource;

    private Logger logger = Logger.getLogger(WebSecurityConfig.class);

    /**
     * 加密方式
     *
     * @return 加密方式
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 记住密码
     * @return 记住密码功能的token
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository=new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }


    /**
     * authenticationManager认证管理器
     * auth.userDetailsService将自定义的用户存放，然后在登录的时候进行比对
     *
     * @param auth
     * @throws Exception
     */
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/smsCode","/login", "/verifyCode", "**.JPG", "/css/**", "/js/**", "/index.html", "/uploadFile/**", "/fonts/**", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(ver, UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                .antMatchers("/smsLogin").permitAll()
                .antMatchers("/menuList","/ws/asset/**").authenticated()//菜单列表仅需登录就能访问
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(filterInvocationSecurityMetadataSource);
                        object.setAccessDecisionManager(accessDecisionManager);
                        return object;
                    }
                })
                .and()
                .rememberMe().rememberMeParameter("rememberMe")
                .rememberMeCookieName("remember-me-cookie")
                .tokenValiditySeconds(2*24*60*60)
                .tokenRepository(persistentTokenRepository())
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")
                .usernameParameter("username").passwordParameter("password")
                .failureHandler(myAuthenticationFailureHandler)
                .successHandler(myAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .apply(smsCodeSecurityConfig)
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .and()
                .csrf().disable().exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().migrateSession()
                .maximumSessions(1).expiredSessionStrategy(myExpiredSessionStrategy);
    }


}
