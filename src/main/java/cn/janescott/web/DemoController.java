package cn.janescott.web;

import cn.janescott.domain.dto.SidebarDTO;
import cn.janescott.domain.dto.UserDTO;
import cn.janescott.repository.mapper.SidebarMapper;
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
    private UserMapper userMapper;

    @Resource
    private SidebarMapper sidebarMapper;

    @RequestMapping("/getUser")
    public UserDTO getUser(String username){
        return userMapper.findOne(username);
    }

    @RequestMapping("/sidebar")
    public SidebarDTO getSidebar(Integer roleId){
        return sidebarMapper.getSidebar(roleId);
    }
}
