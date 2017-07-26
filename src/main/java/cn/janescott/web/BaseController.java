package cn.janescott.web;

import cn.janescott.common.constant.SystemConstants;
import cn.janescott.common.exception.LoginException;
import cn.janescott.common.extra.MyUserDetail;
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
    @Resource
    private CacheHelper cacheHelper;

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

    /**
     * 组装目前访问的详细信息对象
     * @param request
     * @return
     */
    protected CurrentDetailDTO assembleCurrent(HttpServletRequest request) throws Exception {
        MyUserDetail currentUser = getCurrentUser();
        if(null == currentUser){
            // todo 记日志
            throw new LoginException();
        }
        CurrentDetailDTO current = new CurrentDetailDTO();
        current.setUsername(currentUser.getUsername());
        String path = request.getRequestURI();
        String [] info = path.split("/");
        if(info.length == 0 || info.length == 1){
            // todo 这里需要记日记
            return null;
        } else if (info.length == 2 && !StringUtils.isEmpty(info[1])) {
            String module = info[1];
            if (module.equals(SystemConstants.MODULE_HOME)) {
                current.setModuleName(SystemConstants.MODULE_HOME);
                current.setMenuName(SystemConstants.MODULE_HOME_MENU);
            } else {
                current.setModuleName(module);
                current.setMenuName(SystemConstants.MODULE_HOME_MENU);
            }
        } else {
            current.setModuleName(info[1]);
            current.setMenuName(info[2].split("\\.")[0].split("\\?")[0]);
        }
        return current;
    }

    protected void attachSidebar(HttpServletRequest request) throws Exception {
        MyUserDetail currentUser = getCurrentUser();
        if(null == currentUser){
            // todo 记日志
            throw new LoginException();
        }
        SidebarDTO sidebar = cacheHelper.getSidebar(currentUser.getRoleId());
        request.getSession().setAttribute("sidebarDTO", sidebar);
    }

    protected void attachCurrentDetail(HttpServletRequest request) throws Exception{
        request.setAttribute("currentVisitor", assembleCurrent(request));
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
