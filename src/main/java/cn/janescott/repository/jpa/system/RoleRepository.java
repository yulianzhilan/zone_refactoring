package cn.janescott.repository.jpa.system;

import cn.janescott.domain.entity.system.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 角色仓库
 * Created by scott on 2017/7/25.
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findOneByName(String name);
}
