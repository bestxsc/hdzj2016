package wl.hdzj.domain;

import wl.hdzj.common.AddVaild;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Created by micro on 2016/11/15.
 */
public class RelationVO {
    @NotNull
    private Integer rid;
    @NotNull(groups = {AddVaild.class})
    private Integer mid;
    @NotNull(groups = {AddVaild.class})
    private Integer tid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationVO that = (RelationVO) o;
        return Objects.equals(rid, that.rid) &&
                Objects.equals(mid, that.mid) &&
                Objects.equals(tid, that.tid);
    }

    @Override
    public String toString() {
        return "RelationVO{" +
                "rid=" + rid +
                ", mid=" + mid +
                ", tid=" + tid +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(rid, mid, tid);
    }

    public Integer getRid() {

        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
}
