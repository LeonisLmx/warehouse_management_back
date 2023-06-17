package cn.pqz.emsboot.component.security.smscode;

import cn.pqz.emsboot.component.util.MyContants;
import cn.pqz.emsboot.modules.sys.entity.RespBean;
import cn.pqz.emsboot.modules.sys.entity.User;
import cn.pqz.emsboot.modules.sys.mapper.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
@Slf4j
@Component
public class SmsCodeValidateFilter extends OncePerRequestFilter {

    @Autowired
    UserMapper userMapper;
    private static ObjectMapper objectMapper=new ObjectMapper();
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        RespBean respBean = null;
        if (StringUtils.equals("/smsLogin", request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            HttpSession session = request.getSession();
            SmsCode codeInSession = (SmsCode) session.getAttribute(MyContants.SMS_SESSION_KEY);
            String mobileInRequest = request.getParameter("mobile");
            String codeInRequest = request.getParameter("smsCode");
            User user = userMapper.loadUserByUsername(mobileInRequest);
            if (StringUtils.isEmpty(mobileInRequest) || StringUtils.isEmpty(codeInRequest)) {
                respBean = RespBean.error("手机号或验证码不能为空");
            } else if (Objects.isNull(codeInSession)) {
                respBean = RespBean.error("验证码不存在");
            } else if (codeInSession.isExpired()) {
                respBean = RespBean.error("验证码已过期");
            } else if (!codeInSession.getCode().equals(codeInRequest)) {
                respBean = RespBean.error("短信验证码不正确");
            } else if (Objects.isNull(user)) {
                respBean = RespBean.error("手机号未注册");
            }else {
                filterChain.doFilter(request,response);
            }
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(respBean));
            session.removeAttribute(MyContants.SMS_SESSION_KEY);
            return;
        }else {
            filterChain.doFilter(request, response);
        }
    }
    }
