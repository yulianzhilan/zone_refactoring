package cn.janescott.web;

import cn.janescott.common.constant.SystemConstants;
import cn.janescott.common.exception.LoginException;
import cn.janescott.common.auth.MyUserDetail;
import cn.janescott.common.helper.CacheHelper;
import cn.janescott.domain.dto.system.CurrentDetailDTO;
import cn.janescott.domain.dto.system.SidebarDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by scott on 2017/7/25.
 */
public class BaseController {
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
