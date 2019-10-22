package com.haidela.payment.pay.user.domain;

/**
 * 用户
 *
 * @author zhanglize
 * @create 2019/10/22
 */
public class User {

    private Long id;
    /**
     * 公司ID
     */
    private String compId;
    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 密码
     */
    private String password;
    /**
     * 登陆类型
     */
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId == null ? null : compId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

}
