package cn.janescott.web;

import cn.janescott.common.helper.CacheHelper;
import cn.janescott.domain.dto.system.SidebarDTO;
import cn.janescott.domain.dto.system.UserDTO;
import cn.janescott.domain.entity.system.RoleEntity;
import cn.janescott.domain.entity.system.UserEntity;
import cn.janescott.repository.jpa.system.RoleRepository;
import cn.janescott.repository.jpa.system.UserRepository;
import cn.janescott.repository.mapper.system.SidebarMapper;
import cn.janescott.repository.mapper.system.UserMapper;
import org.springframework.util.StringUtils;
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

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    @RequestMapping("/getUser")
    public UserDTO getUser(String username) {
        return userMapper.findOneByUsername(username);
    }

    @RequestMapping("/sidebar")
    public SidebarDTO getSidebar(Integer roleId) {
        return sidebarMapper.getSidebar(roleId);
    }

    @RequestMapping("/getUserByCache")
    public UserDTO getUserByCache(String username) {
        return helper.getUserDTO(username);
    }

    @RequestMapping("/sidebarByCache")
    public SidebarDTO getSidebarByCache(Integer roleId) {
        return helper.getSidebar(roleId);
    }

    @RequestMapping("/insert")
    public void insertInto(String table, String key) {
        helper.insertInto(table, key);
//        if(option == 1){
//            return getUserByCache(key);
//        } else if(option == 2) {
//            return getSidebarByCache(Integer.parseInt(key));
//        } else {
//            return "error";
//        }
    }

    @RequestMapping("/getUserEntity")
    public UserEntity getUserEntity(String username, Integer userId) {
        if (!StringUtils.isEmpty(username)) {
            return userRepository.findOneByUsername(username);
        } else if (userId != null) {
            return userRepository.findOne(userId);
        } else {
            return null;
        }
    }

    @RequestMapping("/getRoleEntity")
    public RoleEntity getRoleEntity(String name, Integer id){
        if(!StringUtils.isEmpty(name)){
            return roleRepository.findOneByName(name);
        } else if(id != null) {
            return roleRepository.getOne(id);
        } else {
            return null;
        }
    }
}
