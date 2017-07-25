package cn.janescott.repository.jpa.system;

import cn.janescott.domain.entity.system.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户仓库
 * Created by scott on 2017/7/25.
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findOneByUsername(String username);
}
