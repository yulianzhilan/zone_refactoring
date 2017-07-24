package cn.janescott.common.helper;

import cn.janescott.domain.dto.SidebarDTO;
import cn.janescott.repository.mapper.SidebarMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by scott on 2017/7/24.
 */
@Component
public class CacheHelper {
    @Resource
    private SidebarMapper sidebar;

    @Cacheable(cacheNames = {"sidebar"}, key = "'getSidebar:roleId' + @args")
    public SidebarDTO getSidebar(Integer roleId){
        return sidebar.getSidebar(roleId);
    }




}
