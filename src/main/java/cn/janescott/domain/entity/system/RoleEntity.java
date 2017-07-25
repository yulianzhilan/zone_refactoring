package cn.janescott.domain.entity.system;

import cn.janescott.domain.BaseSerialDomain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by scott on 2017/7/25.
 */
@Entity
@Table(name = "t_role")
// jackson中防止lazy加载
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class RoleEntity extends BaseSerialDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
