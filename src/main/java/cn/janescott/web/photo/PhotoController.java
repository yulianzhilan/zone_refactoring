package cn.janescott.web.photo;

import cn.janescott.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/photo")
public class PhotoController extends BaseController {

    @RequestMapping(method = {RequestMethod.GET})
    public String execute() {
        return "views/photo/index";
    }
}
