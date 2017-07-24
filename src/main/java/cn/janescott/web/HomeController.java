package cn.janescott.web;

import cn.janescott.common.helper.CacheHelper;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by scott on 2017/7/24.
 */
@Controller
public class HomeController {

    @Resource
    private CacheHelper cacheHelper;

    @Resource
    private StringEncryptor encryptor;

    @RequestMapping("/home")
    public String home(){
        return "index";
    }

    @RequestMapping("/definition")
    public String definition(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String accessToken = (String)session.getAttribute("accessToken");
        if(StringUtils.isEmpty(accessToken)){
            throw new Exception("没有登录!");
        }
        encryptor.decrypt(accessToken);
//        cacheHelper.getSidebar()

        return "definition/default";
    }

}
