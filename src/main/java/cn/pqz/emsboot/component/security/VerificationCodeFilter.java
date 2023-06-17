package cn.pqz.emsboot.component.security;

import cn.pqz.emsboot.modules.sys.entity.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class VerificationCodeFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if ("POST".equals(req.getMethod()) && "/doLogin".equals(req.getServletPath())) {
            //登录请求
            String code = req.getParameter("code");
            String verify_code = (String) req.getSession().getAttribute("verify_code");
            if (verify_code == null || code == null || "".equals(code) || !verify_code.toLowerCase().equals(code.toLowerCase())) {
                resp.setContentType("application/json;charset=utf-8");
                RespBean respBean = RespBean.error("验证码填写错误");
                PrintWriter out = resp.getWriter();
                ObjectMapper om = new ObjectMapper();
                out.write(om.writeValueAsString(respBean));
                out.flush();
                out.close();
                return;
            } else {
                filterChain.doFilter(req, resp);
            }
        } else {
            //如果不是登录请求直接过
            filterChain.doFilter(req, resp);
        }
    }
}
