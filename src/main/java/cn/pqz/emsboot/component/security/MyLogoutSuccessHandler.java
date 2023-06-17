package cn.pqz.emsboot.component.security;

import cn.pqz.emsboot.modules.sys.entity.RespBean;
import cn.pqz.emsboot.modules.sys.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    private static ObjectMapper objectMapper=new ObjectMapper();
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        User user= (User) authentication.getPrincipal();
        RespBean respBean=null;
        response.setContentType("application/json;charset=utf-8");
        respBean=RespBean.ok("尊敬的"+user.getUsername()+"您已经注销登录成功");
        response.getWriter().write(objectMapper.writeValueAsString(respBean));
    }
}
