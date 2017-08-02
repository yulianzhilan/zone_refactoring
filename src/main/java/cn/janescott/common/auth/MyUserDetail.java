package cn.janescott.common.auth;

import cn.janescott.domain.dto.system.RoleDTO;
import cn.janescott.domain.entity.system.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 验证对象
 * Created by scott on 2017/7/25.
 * @Url http://blog.csdn.net/tzdwsy/article/details/50738043
 */
public class MyUserDetail extends UserEntity implements UserDetails {

    // 这里可以是一个List，对于多用户角色的系统可以设置为List
    // 这里由于hibernate的懒加载问题，始终取不到数据，后来采用mybatis解决
    // 尝试使用OpenSessionInViewFilter也没有成功
    private RoleDTO roleDTO;

    public MyUserDetail(UserEntity userEntity, RoleDTO roleDTO) {
        super(userEntity);
        this.roleDTO = roleDTO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(roleDTO == null){
            return AuthorityUtils.commaSeparatedStringToAuthorityList("");
        }
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roleDTO.getName());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.getFlag();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }
}
