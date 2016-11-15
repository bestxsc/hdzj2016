package wl.hdzj.domain;

import wl.hdzj.entity.Member;
import wl.hdzj.entity.News;

import java.util.List;

public class TeamVO{
    private Integer tid;
    private Integer del;
    private String desride;
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
