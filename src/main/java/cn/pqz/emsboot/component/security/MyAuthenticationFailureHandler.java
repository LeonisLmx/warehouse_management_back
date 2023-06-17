package cn.pqz.emsboot.component.security;

import cn.pqz.emsboot.modules.sys.entity.RespBean;
import cn.pqz.emsboot.modules.sys.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        User user = new User();
        logger.info("----------" + user.getUsername() + "登录失败" + "----------");
        RespBean respBean = null;
        if (user.isEnabled()) {
            respBean = RespBean.error("账号已被冻结，请联系管理员解冻");
            return;
        }
        if (e instanceof BadCredentialsException ||
                e instanceof UsernameNotFoundException) {
            respBean = RespBean.error("用户名或密码输入错误");
        } else if (e instanceof LockedException) {
            respBean = RespBean.error("账户被锁定，请联系管理员");
        } else if (e instanceof CredentialsExpiredException) {
            respBean = RespBean.error("密码过期，请联系管理员");
        } else if (e instanceof AccountExpiredException) {
            respBean = RespBean.error("账户过期请联系管理员");
        } else if (e instanceof DisabledException) {
            respBean = RespBean.error("账户被禁用，请联系管理员");
        } else {
            respBean = RespBean.error("登录失败");
        }
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.write(om.writeValueAsString(respBean));
        out.flush();
        out.close();
    }
}
