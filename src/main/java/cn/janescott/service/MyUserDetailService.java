package cn.janescott.service;

import cn.janescott.common.constant.ExceptionConstants;
import cn.janescott.common.auth.MyUserDetail;
import cn.janescott.domain.dto.system.RoleDTO;
import cn.janescott.domain.entity.system.UserEntity;
import cn.janescott.repository.jpa.system.UserRepository;
import cn.janescott.repository.mapper.system.RoleMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by scott on 2017/7/25.
 */
@Service("userDetailService")
public class MyUserDetailService implements UserDetailsService {
    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity;
        try {
            userEntity = userRepository.findOneByUsername(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException(ExceptionConstants.ERROR_L00);
        }
        if (userEntity == null) {
            throw new UsernameNotFoundException(ExceptionConstants.ERROR_L01);
        } else {
            try {
                RoleDTO roleDTO = roleMapper.findOne(userEntity.getRoleId());
                return new MyUserDetail(userEntity, roleDTO);
            } catch (Exception e) {
                throw new UsernameNotFoundException(ExceptionConstants.ERROR_L06);
            }
        }
    }
}
