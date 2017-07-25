package cn.janescott.domain.entity.system;

import cn.janescott.domain.BaseSerialDomain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户实体类
 * Created by scott on 2017/7/25.
 */
@Entity
@Table(name = "t_user")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class UserEntity extends BaseSerialDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "MODIFY_TIME")
    private Date modifyTime;

    private String email;

    private Boolean flag;

    @Column(name = "ROLE_ID")
    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public UserEntity() {
    }

    public UserEntity(UserEntity userEntity){
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.roleId = userEntity.getRoleId();
        this.id = userEntity.getId();
        this.createTime = userEntity.getCreateTime();
        this.modifyTime = userEntity.getModifyTime();
        this.flag = userEntity.getFlag();
        this.email = userEntity.getEmail();
    }
}
