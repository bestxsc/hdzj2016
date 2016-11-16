package wl.hdzj.domain;

import org.hibernate.validator.constraints.NotBlank;
import wl.hdzj.common.AddVaild;
import wl.hdzj.common.UpdateVaild;

import javax.validation.constraints.NotNull;

public class TeamVO{
    @NotNull(groups = {UpdateVaild.class})
    private Integer tid;
    private Integer del;
    private String desride;
    @NotBlank(groups = {AddVaild.class})
    private String name;
    private String pic;

    @Override
    public String toString() {
        return "TeamVO{" +
                "tid=" + tid +
                ", del=" + del +
                ", desride='" + desride + '\'' +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public String getDesride() {
        return desride;
    }

    public void setDesride(String desride) {
        this.desride = desride;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
