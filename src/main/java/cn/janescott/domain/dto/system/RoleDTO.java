package cn.janescott.domain.dto.system;

import cn.janescott.domain.BaseSerialDomain;

/**
 * 角色对象
 * Created by scott on 2017/7/25.
 */
public class RoleDTO extends BaseSerialDomain{
    private String name;

    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
