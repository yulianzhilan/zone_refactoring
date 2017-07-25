package cn.janescott.web;

import cn.janescott.common.helper.CacheHelper;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by scott on 2017/7/24.
 */
@Controller
public class HomeController extends BaseController {

    @Resource
    private CacheHelper cacheHelper;

    @Resource
    private StringEncryptor encryptor;

    @RequestMapping("/home")
    public String home() {
        return "index";
    }

    @RequestMapping("/definition")
    public String definition(HttpServletRequest request, HttpServletResponse response) throws Exception {
        getCurrentUser();
        isLogin();
        return "definition/default";
    }

}
