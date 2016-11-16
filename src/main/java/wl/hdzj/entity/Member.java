package wl.hdzj.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "member", schema = "hdzj_2016")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Member {
    private Integer mid;
    private String name;
    private Short sex;
    private String jobtitle;
    private String subject;
    private String describe;
    private String pic;
    private Short identify;
    private Short isshow;
    private String xueyuan;
    private List<Team> rTeams;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mid", nullable = false)
    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    @Basic
    @Column(name = "name", length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "sex")
    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "jobtitle")
    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    @Basic
    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "describe", length = 4000)
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Basic
    @Column(name = "pic")
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Basic
    @Column(name = "identify")
    public Short getIdentify() {
        return identify;
    }

    public void setIdentify(Short identify) {
        this.identify = identify;
    }

    @Basic
    @Column(name = "isshow")
    public Short getIsshow() {
        return isshow;
    }

    public void setIsshow(Short isshow) {
        this.isshow = isshow;
    }

    @Basic
    @Column(name = "xueyuan")
    public String getXueyuan() {
        return xueyuan;
    }

    public void setXueyuan(String xueyuan) {
        this.xueyuan = xueyuan;
    }

    @Override
    public String toString() {
        return "Member{" +
                "mid=" + mid +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", jobtitle='" + jobtitle + '\'' +
                ", subject='" + subject + '\'' +
                ", describe='" + describe + '\'' +
                ", pic='" + pic + '\'' +
                ", identify=" + identify +
                ", isshow=" + isshow +
                ", xueyuan='" + xueyuan + '\'' +
                ", rTeams=" + rTeams +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(mid, member.mid) &&
                Objects.equals(name, member.name) &&
                Objects.equals(sex, member.sex) &&
                Objects.equals(jobtitle, member.jobtitle) &&
                Objects.equals(subject, member.subject) &&
                Objects.equals(describe, member.describe) &&
                Objects.equals(pic, member.pic) &&
                Objects.equals(identify, member.identify) &&
                Objects.equals(isshow, member.isshow) &&
                Objects.equals(xueyuan, member.xueyuan) &&
                Objects.equals(rTeams, member.rTeams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mid, name, sex, jobtitle, subject, describe, pic, identify, isshow, xueyuan, rTeams);
    }

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "relation", schema = "hdzj_2016",
            joinColumns = @JoinColumn(name = "mid", referencedColumnName = "mid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "tid", referencedColumnName = "tid", nullable = false))
    public List<Team> getRTeams() {
        return rTeams;
    }

    private void setRTeams(List<Team> rTeams) {
        this.rTeams = rTeams;
    }
}
