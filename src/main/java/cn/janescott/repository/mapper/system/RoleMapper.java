package cn.janescott.repository.mapper.system;

import cn.janescott.domain.dto.system.RoleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by scott on 2017/7/25.
 */
@Mapper
@Component
public interface RoleMapper {
    @Select("select * from t_role where id = #{id}")
    RoleDTO findOne(Integer id);
}
