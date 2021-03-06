package cn.janescott.domain.dto.system;

import cn.janescott.domain.BaseSerialDomain;

/**
 * Created by scott on 2017/7/26.
 */
public class CurrentDetailDTO extends BaseSerialDomain{
    private String moduleName;

    private String menuName;

    private String username;

    private boolean index = false;

    private String parentPath;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isIndex() {
        return index;
    }

    public void setIndex(boolean index) {
        this.index = index;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }
}
