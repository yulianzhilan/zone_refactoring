package cn.janescott.web;

import cn.janescott.common.helper.CacheHelper;
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

    @Resource
    private CacheHelper helper;

    @RequestMapping("/getUser")
    public UserDTO getUser(String username){
        return userMapper.findOneByUsername(username);
    }

    @RequestMapping("/sidebar")
    public SidebarDTO getSidebar(Integer roleId){
        return sidebarMapper.getSidebar(roleId);
    }

    @RequestMapping("/getUserByCache")
    public UserDTO getUserByCache(String username){
        return helper.getUserDTO(username);
    }

    @RequestMapping("/sidebarByCache")
    public SidebarDTO getSidebarByCache(Integer roleId){
        return helper.getSidebar(roleId);
    }

    @RequestMapping("/insert")
    public void insertInto(String table, String key){
        helper.insertInto(table, key);
//        if(option == 1){
//            return getUserByCache(key);
//        } else if(option == 2) {
//            return getSidebarByCache(Integer.parseInt(key));
//        } else {
//            return "error";
//        }
    }

}
