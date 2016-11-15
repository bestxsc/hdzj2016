package wl.hdzj.domain;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by lipengbiao on 2016/11/14.
 */
public class UserVO {
    private Integer uid;

    @NotBlank(message = "用户名不能为空")
    private String account;
    @NotBlank(message = "姓名不能为空")
    private String name;
    @NotBlank(message = "密码不能为空")
    private String passwd;
    private String phone;
    private Short sex;
    @NotBlank(message = "权限角色不能为空")
    private String role;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //密码不可见
    private String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "uid=" + uid +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", passwd='" + passwd + '\'' +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                ", role='" + role + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
