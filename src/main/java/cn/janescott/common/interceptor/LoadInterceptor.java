package cn.janescott.common.interceptor;

import cn.janescott.common.auth.MyUserDetail;
import cn.janescott.common.constant.SystemConstants;
import cn.janescott.common.exception.LoginException;
import cn.janescott.common.helper.CacheHelper;
import cn.janescott.domain.dto.system.CurrentDetailDTO;
import cn.janescott.domain.dto.system.SidebarDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by scott on 2017/8/2.
 */
@Component
public class LoadInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private CacheHelper cacheHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        if (SystemConstants.ERROR_PATH.equals(path) || path.contains(SystemConstants.ERROR)) {

        } else {
            if (request.getSession().getAttribute("sidebarDTO") == null) {
                attachSidebar(request);
            }
            attachCurrent(request, path);
        }
        return true;
    }

    /**
     * 添加目前访问的详细信息对象
     */
    private void attachCurrent(HttpServletRequest request, String path) throws Exception {
        MyUserDetail currentUser = getCurrentUser();
        if(null == currentUser){
            // todo 记日志
            throw new LoginException();
        }
        CurrentDetailDTO current = new CurrentDetailDTO();
        current.setUsername(currentUser.getUsername());
        String [] info = path.split("/");
        if (info.length == 3 && !StringUtils.isEmpty(info[2])) {
            String module = info[2];
            if (module.equals(SystemConstants.MODULE_HOME)) {
                current.setModuleName(SystemConstants.MODULE_HOME);
                current.setMenuName(SystemConstants.MODULE_HOME_MENU);
                current.setIndex(true);
            } else {
                current.setModuleName(module);
                current.setMenuName(SystemConstants.MODULE_INDEX_MENU);
                current.setIndex(true);
            }
            current.setParentPath(path);
        } else {
            current.setModuleName(info[2]);
            current.setMenuName(info[3].split("\\.")[0].split("\\?")[0]);
            current.setParentPath(path.substring(0, path.split("\\.")[0].split("\\?")[0].lastIndexOf("/")));
        }
        request.setAttribute("currentVisitor", current);
    }

    /**
     * 添加菜单信息
     */
    private void attachSidebar(HttpServletRequest request) throws Exception {
        MyUserDetail currentUser = getCurrentUser();
        if(null == currentUser){
            // todo 记日志
            throw new LoginException();
        }
        SidebarDTO sidebar = cacheHelper.getSidebar(currentUser.getRoleId());
        request.getSession().setAttribute("sidebarDTO", sidebar);
        request.getSession().setAttribute("homePath", SystemConstants.HOME_PATH);
        request.getSession().setAttribute("ctx", SystemConstants.CONTENT_PATH);
    }

    /**
     * 从security上下文中取用户登录信息
     */
    private MyUserDetail getCurrentUser() {
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
}
