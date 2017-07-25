package cn.janescott.repository.mapper.system;

import cn.janescott.domain.dto.system.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by scott on 2017/7/21.
 */
@Mapper
@Component
public interface UserMapper {
    @Select("select u.*, r.name from t_user u,t_role r where u.role_id = r.id and u.username=#{username}")
    @Results({
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "roleName", column = "name")
    })
    UserDTO findOneByUsername(String username);
}
