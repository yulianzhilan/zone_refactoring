package cn.janescott.web;

import cn.janescott.common.exception.LoginException;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by scott on 2017/7/24.
 */
@Controller
public class HomeController extends BaseController {


    @Resource
    private StringEncryptor encryptor;

    @RequestMapping("/home")
    public String home(HttpServletRequest request) {
        try {
            attachSidebar(request);
        } catch (LoginException e) {
            return "redirect:/login";
        } catch (Exception e) {
            // todo 这里需要记日志
        }
        attachCurrentDetail(request);
        return "views/home";
    }

    @RequestMapping("/default")
    public String definition() {
        return "definition/default";
    }

    @RequestMapping("/article")
    public String article(HttpServletRequest request) throws Exception{
        attachSidebar(request);
        attachCurrentDetail(request);
        return "views/article/index";
    }
}
