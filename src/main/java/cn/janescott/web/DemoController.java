package cn.janescott.web;

import cn.janescott.domain.dto.UserDTO;
import cn.janescott.repository.mapper.UserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by scott on 2017/7/21.
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private UserMapper mapper;

    @RequestMapping("/mapper")
    public UserDTO mapper(String username){
        return mapper.findOne(username);
    }
}
