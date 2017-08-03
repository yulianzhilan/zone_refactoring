package cn.janescott.configuration;

import cn.janescott.common.filter.RedirectFilter;
import cn.janescott.common.interceptor.LoadInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;
import java.nio.charset.Charset;

/**
 * Created by scott on 2017/7/24.
 */
@Configuration
@EnableWebMvc
public class WebMvcConfiguration extends WebMvcConfigurerAdapter{

    @Resource
    private LoadInterceptor loadInterceptor;

    /**
     * 文件上传解析器
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }

    /**
     * url http://blog.csdn.net/wujianmin577/article/details/61197485
     * 解决Controller中文乱码问题
     * @return
     */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter(){
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

//    /**
//     * 以下两个bean是为了监控Druid
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean getFilterRegistrationBean(){
//        FilterRegistrationBean filter = new FilterRegistrationBean();
//        filter.setFilter(new WebStatFilter());
//        filter.setName("druidWebStatFilter");
//        filter.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
//        filter.addUrlPatterns("/*");
//        return filter;
//    }
//
//    @Bean
//    public ServletRegistrationBean getServletRegistrationBean(){
//        ServletRegistrationBean servlet = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
//        servlet.setName("druidStatViewServlet");
//        servlet.addInitParameter("resetEnable", "true");
//        return servlet;
//    }

    /**
     * 过滤器
     */
    @Bean
    public FilterRegistrationBean registrationBean(){
        FilterRegistrationBean register = new FilterRegistrationBean();
        register.setFilter(new RedirectFilter());
        register.addUrlPatterns("/*");
        return register;
    }

    /**
     * 对于无任何业务处理的请求（只是简单页面跳转），直接在这里配置页面和路径
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
    }

    /**
     * 静态资源配置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loadInterceptor);
    }
}
