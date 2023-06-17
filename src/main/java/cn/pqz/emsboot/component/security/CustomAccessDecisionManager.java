package cn.pqz.emsboot.component.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * 角色信息的对比
 * 访问决策管理器
 */
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    /**
     * @param auth       包含登录用户的信息
     * @param o          FilterInvocation对象
     * @param collection FilterInvocationSecurityMetadataSource 的返回值
     *                   角色对比：如果需要的角色是ROLE_LOGIN，说明当前请求的URL用户信息登录后即可访问，如果auth
     *                   是UsernamePasswordAuthenticationToken实例，说明当前用户已经登陆。
     *                   如果当前用户具备当前请求所需要的角色，那么方法结束
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication auth,
                       Object o,
                       Collection<ConfigAttribute> collection)
            throws AccessDeniedException, InsufficientAuthenticationException {
        Iterator<ConfigAttribute> iterator = collection.iterator();//当前请求所需要的角色
        while (iterator.hasNext()) {
            ConfigAttribute ca = iterator.next();
            String needRole = ca.getAttribute();
            if ("ROLE_LOGIN".equals(needRole)) {
                if (auth instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException("未登录");
                } else
                    return;
            }
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();//当前用户所拥有的角色
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }

        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
