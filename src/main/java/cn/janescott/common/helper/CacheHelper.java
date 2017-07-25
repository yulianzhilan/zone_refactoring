package cn.janescott.common.helper;

import cn.janescott.domain.dto.system.SidebarDTO;
import cn.janescott.domain.dto.system.UserDTO;
import cn.janescott.repository.mapper.system.SidebarMapper;
import cn.janescott.repository.mapper.system.UserMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 缓存帮助类
 * Created by scott on 2017/7/24.
 */
@Component
public class CacheHelper {
    @Resource
    private SidebarMapper sidebar;

    @Resource
    private UserMapper userMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Cacheable(cacheNames = {"sidebar"}, key = "'getSidebar:roleId@' + args")
    public SidebarDTO getSidebar(Integer roleId){
        return sidebar.getSidebar(roleId);
    }

    @Cacheable(cacheNames = {"user"}, key = "'getUserDTO:userId@' + args")
    public UserDTO getUserDTO(String username){
        return userMapper.findOneByUsername(username);
    }

    public int insertInto(String table, String key){
        if("user".equals(table)){
            UserDTO userDTO = userMapper.findOneByUsername(key);
//            redisTemplate.boundZSetOps(table).intersectAndStore("getUserDTO:userId@" + key, userDTO);
            return 1;
        } else if("sidebar".equals(table)){
            SidebarDTO sidebarDTO = sidebar.getSidebar(Integer.parseInt(key));
            redisTemplate.boundZSetOps(table).getOperations().opsForValue().set("getSidebar:roleId@" + key, sidebarDTO);
            return 2;
        }
        return 0;
    }

}
