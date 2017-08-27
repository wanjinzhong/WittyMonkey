package com.wittymonkey.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单实体，并不保存任何菜单功能及详细内容。
 * 只是为了为权限赋予操作这些菜单的权力
 *
 * @author Neil
 */
@Entity
@Table(name = "menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 20)
    private String name;

    @Column
    private String description;

    @ManyToMany(targetEntity = Role.class, mappedBy = "menus")
    private List<Role> roles = new ArrayList<Role>();

    // 是否可配置该菜单
    @Column
    private Boolean configurable;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Boolean getConfigurable() {
        return configurable;
    }

    public void setConfigurable(Boolean configurable) {
        this.configurable = configurable;
    }
}
