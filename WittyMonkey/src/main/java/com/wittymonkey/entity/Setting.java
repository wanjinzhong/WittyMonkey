package com.wittymonkey.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 设置
 *
 * @author neilw
 */
@Entity
@Table(name = "setting")
public class Setting implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(targetEntity = User.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // 语言
    @Column(columnDefinition = "VARCHAR(10) default 'zh_CN'")
    private String lang;

    // 每页数据条数
    @Column(columnDefinition = "int default 10")
    private Integer pageSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
