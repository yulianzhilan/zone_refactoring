package cn.janescott.domain.dto.system;

import cn.janescott.domain.BaseSerialDomain;

import java.util.List;

/**
 * 侧边栏对象
 * Created by scott on 2017/7/24.
 */
public class SidebarDTO extends BaseSerialDomain{
    private Integer RoleId;

    private String RoleName;

    private List<ModuleDTO> modules;

    public Integer getRoleId() {
        return RoleId;
    }

    public void setRoleId(Integer roleId) {
        RoleId = roleId;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public List<ModuleDTO> getModules() {
        return modules;
    }

    public void setModules(List<ModuleDTO> modules) {
        this.modules = modules;
    }

    public SidebarDTO() {
    }

    public SidebarDTO(List<ModuleDTO> modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return "SidebarDTO{" +
                "RoleId=" + RoleId +
                ", RoleName='" + RoleName + '\'' +
                ", modules=" + modules +
                '}';
    }
}
