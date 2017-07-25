package cn.janescott.repository.mapper.system;

import cn.janescott.domain.dto.system.SidebarDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by scott on 2017/7/24.
 */
@Component
public interface SidebarMapper {
    SidebarDTO getSidebar(@Param("roleId") Integer roleId);
}
