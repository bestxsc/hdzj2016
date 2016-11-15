package wl.hdzj.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "relation", schema = "hdzj_2016")
public class Relation {
    private Integer rid;
    private Integer mid;
    private Integer tid;

    @Id
    @javax.persistence.Column(name = "rid", nullable = false)
    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    @Basic
    @Column(name = "mid", nullable = false)
    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    @Basic
    @Column(name = "tid", nullable = false)
    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "rid=" + rid +
                ", mid=" + mid +
                ", tid=" + tid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relation relation = (Relation) o;
        return Objects.equals(rid, relation.rid) &&
                Objects.equals(mid, relation.mid) &&
                Objects.equals(tid, relation.tid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rid, mid, tid);
    }
}
