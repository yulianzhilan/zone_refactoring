package cn.janescott.common.filter;

import cn.janescott.common.constant.SystemConstants;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by scott on 2017/8/3.
 */
public class RedirectFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String path = ((HttpServletRequest)request).getRequestURI();
        if (StringUtils.isEmpty(path) || SystemConstants.CONTENT_PATH.equals(path)) {
//            request.getRequestDispatcher("/home").forward(request,response);
            ((HttpServletResponse)response).sendRedirect(SystemConstants.HOME_PATH);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
