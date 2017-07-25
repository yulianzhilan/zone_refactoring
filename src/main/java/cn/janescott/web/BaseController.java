package cn.janescott.web;

import cn.janescott.common.extra.MyUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by scott on 2017/7/25.
 */
public class BaseController {

    protected MyUserDetail getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            Authentication authentication = context.getAuthentication();
            if (authentication != null) {
                Object principal = authentication.getPrincipal();
                if (principal != null) {
                    if (principal instanceof MyUserDetail) {
                        return (MyUserDetail) principal;
                    }
                }
            }
        }
        return null;
    }

    protected boolean isLogin() {
        return getCurrentUser() != null;
    }

    /*
     * 其他获取security 的方式
     *
     HttpSession session = request.getSession(true);
     SecurityContextImpl securityContext = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
     MyUserDetail myUserDetail = (MyUserDetail) securityContext.getAuthentication().getPrincipal();
     System.out.println(myUserDetail);
     if(credentials instanceof MyUserDetail){
     System.out.println(((MyUserDetail) credentials).getUsername());
     }
     cacheHelper.getSidebar()
     *
     */
}
