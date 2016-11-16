package wl.hdzj.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "columnnn", schema = "hdzj_2016")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Columnnn {
    private Integer cid;
    private String name;
    private Integer parterid;

    private List<News> lNews;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Columnnn columnnn = (Columnnn) o;
        return Objects.equals(cid, columnnn.cid) &&
                Objects.equals(name, columnnn.name) &&
                Objects.equals(parterid, columnnn.parterid) &&
                Objects.equals(lNews, columnnn.lNews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, name, parterid, lNews);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @javax.persistence.Column(name = "cid", nullable = false)
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Basic
    @javax.persistence.Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @javax.persistence.Column(name = "parterid")
    public Integer getParterid() {
        return parterid;
    }

    public void setParterid(Integer parterid) {
        this.parterid = parterid;
    }

    //禁止无限递归
    @JsonIgnore
    @OneToMany(mappedBy = "LColumn", cascade = {})
    public List<News> getLNews() {
        return lNews;
    }

    private void setLNews(List<News> INews) {
        this.lNews = INews;
    }

    @Override
    public String toString() {
        return "Columnnn{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", parterid=" + parterid +
                ", lNews=" + lNews +
                '}';
    }
}
