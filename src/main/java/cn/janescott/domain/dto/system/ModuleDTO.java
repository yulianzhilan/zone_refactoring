package cn.janescott.domain.dto.system;

import cn.janescott.domain.BaseSerialDomain;

import java.util.List;

/**
 * 模块对象
 * Created by scott on 2017/7/24.
 */
public class ModuleDTO extends BaseSerialDomain{
    private Integer id;

    private String icon;

    private Integer seq;

    private String name;

    private List<MenuDTO> menus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDTO> menus) {
        this.menus = menus;
    }
}
