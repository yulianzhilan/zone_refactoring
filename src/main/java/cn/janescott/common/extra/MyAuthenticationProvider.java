package cn.janescott.common.extra;

import cn.janescott.common.constant.ExceptionConstants;
import cn.janescott.service.MyUserDetailService;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Created by scott on 2017/7/25.
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Resource(name = "userDetailService")
    private MyUserDetailService userDetailService;

    @Resource(name = "encryptor")
    private StringEncryptor encryptor;

    // 自定义验证方式
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        // 在这里可以对用户名和密码做一些有效性验证，防止前端传入非法无效字符
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {

        }


        // 从数据库获取用户信息
        MyUserDetail userDetail = (MyUserDetail) userDetailService.loadUserByUsername(username);
        // 没有找到用户
        if (userDetail == null) {
            throw new UsernameNotFoundException(ExceptionConstants.ERROR_L01);
            // 用户不可用
        } else if (!userDetail.isEnabled()) {
            throw new DisabledException("");
            // 用户过期
        } else if (!userDetail.isAccountNonExpired()) {

            // 用户被锁定
        } else if (!userDetail.isAccountNonLocked()) {

            // 用户凭证过期
        } else if (!userDetail.isCredentialsNonExpired()) {

        }
        // 密码验证
        // 加密/解密
        if (!encryptor.decrypt(userDetail.getPassword()).equals(password)) {
            throw new BadCredentialsException("密码不对");
        }

        return new UsernamePasswordAuthenticationToken(userDetail, userDetail.getPassword(), userDetail.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 返回true后才会执行上面的authenticate方法，这步验证可以确保authentication能正确转换类型
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
