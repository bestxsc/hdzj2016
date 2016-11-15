package wl.hdzj.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "team", schema = "hdzj_2016")
public class Team{
    private Integer tid;
    private Integer del;
    private String desride;
    private String name;
    private String pic;

    private List<Member> rMember;
    private List<News> lNews;

    @Override
    public String toString() {
        return "Team{" +
                "tid=" + tid +
                ", del=" + del +
                ", desride='" + desride + '\'' +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", rMember=" + rMember +
                ", lNews=" + lNews +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(tid, team.tid) &&
                Objects.equals(del, team.del) &&
                Objects.equals(desride, team.desride) &&
                Objects.equals(name, team.name) &&
                Objects.equals(pic, team.pic) &&
                Objects.equals(rMember, team.rMember) &&
                Objects.equals(lNews, team.lNews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tid, del, desride, name, pic, rMember, lNews);
    }

    @ManyToMany(mappedBy = "RTeams",cascade = {}, fetch = FetchType.LAZY)
    @JsonBackReference()
    public List<Member> getRMember() {
        return rMember;
    }

    private void setRMember(List<Member> rMember){
        this.rMember = rMember;
    }

    @OneToMany(mappedBy = "LTeams", cascade = {}, fetch = FetchType.LAZY)
    @JsonBackReference()
    public List<News> getlNews() {
        return lNews;
    }

    private void setlNews(List<News> lNews) {
        this.lNews = lNews;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tid", nullable = false)
    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @Basic
    @Column(name = "del", nullable = false)
    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    @Basic
    @Column(name = "desride", nullable = false, length = 4000)
    public String getDesride() {
        return desride;
    }

    public void setDesride(String desride) {
        this.desride = desride;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "pic", nullable = false, length = 255)
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

}
