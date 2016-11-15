package wl.hdzj.domain;

import org.hibernate.validator.constraints.NotBlank;
import wl.hdzj.common.AddVaild;
import wl.hdzj.common.UpdateVaild;

import javax.validation.constraints.NotNull;

public class ColumnVO {
    @NotNull(message = "栏目ID不能为空", groups = {UpdateVaild.class})
    private Integer cid;

    @NotBlank(message = "栏目名称不为空", groups = {AddVaild.class})
    private String name;

    private Integer parterid;

    @Override
    public String toString() {
        return "ColumnVO{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", parterid=" + parterid +
                '}';
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParterid() {
        return parterid;
    }

    public void setParterid(Integer parterid) {
        this.parterid = parterid;
    }
}
