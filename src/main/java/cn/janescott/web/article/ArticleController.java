package cn.janescott.web.article;

import cn.janescott.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by scott on 2017/8/2.
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @RequestMapping(method = {RequestMethod.GET})
    public String execute(HttpServletRequest request) throws Exception {
        return "views/article/index";
    }

    @RequestMapping(value = "/personal")
    public String personal() {
        return "views/article/personal";
    }

}
