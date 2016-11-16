package wl.hdzj.domain;

import org.hibernate.validator.constraints.NotBlank;
import wl.hdzj.common.AddVaild;
import wl.hdzj.common.UpdateVaild;

import javax.validation.constraints.NotNull;

/**
 * Created by lipengbiao on 2016/11/14.
 */
public class UserVO {
    @NotNull(message = "用户ID不能为空", groups = {UpdateVaild.class})
    private Integer uid;

    @NotBlank(message = "用户名不能为空", groups = {AddVaild.class})
    private String account;
    @NotBlank(message = "姓名不能为空", groups = {AddVaild.class})
    private String name;
    @NotBlank(message = "密码不能为空", groups = {AddVaild.class})
    private String passwd;
    private String phone;
    private Short sex;
    @NotBlank(message = "角色不能为空", groups = {AddVaild.class})
    private Short role;

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

    public Short getRole() {
        return role;
    }

    public void setRole(Short role) {
        this.role = role;
    }
}
