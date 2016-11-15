package wl.hdzj.entity;

import groovy.transform.BaseScript;
import org.springframework.beans.factory.annotation.Autowired;
import wl.hdzj.dao.TeamRepository;

import javax.persistence.*;
import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "news", schema = "hdzj_2016")
public class News {
    private Integer nid;
    private String auther;
    private Timestamp date;
    private Short isdraft;
    private Short istop;
    private String message;
    private String pic;
    private String subtitle;
    private String title;
    private Short type;

    private Integer cid;
    private Integer tid;

    //只读(update = false, insert = false)
    private Columnnn lColumn;
    private Team lTeams;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(nid, news.nid) &&
                Objects.equals(auther, news.auther) &&
                Objects.equals(date, news.date) &&
                Objects.equals(isdraft, news.isdraft) &&
                Objects.equals(istop, news.istop) &&
                Objects.equals(message, news.message) &&
                Objects.equals(pic, news.pic) &&
                Objects.equals(subtitle, news.subtitle) &&
                Objects.equals(title, news.title) &&
                Objects.equals(type, news.type) &&
                Objects.equals(lColumn, news.lColumn) &&
                Objects.equals(lTeams, news.lTeams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nid, auther, date, isdraft, istop, message, pic, subtitle, title, type, lColumn, lTeams);
    }

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "cid", referencedColumnName = "cid", updatable = false, insertable = false)
    public Columnnn getLColumn() {
        return lColumn;

    }

    public void setLColumn(Columnnn lColumn) {
        this.lColumn = lColumn;
    }

    @Basic
    @Column(name = "cid", nullable = false)
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "nid", nullable = false)
    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    @Basic
    @Column(name = "auther", nullable = false)
    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "News{" +
                "nid=" + nid +
                ", auther='" + auther + '\'' +
                ", date=" + date +
                ", isdraft=" + isdraft +
                ", istop=" + istop +
                ", message='" + message + '\'' +
                ", pic='" + pic + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                '}';
    }

    @Basic
    @Column(name = "isdraft", nullable = false)
    public Short getIsdraft() {
        return isdraft;
    }

    public void setIsdraft(Short isdraft) {
        this.isdraft = isdraft;
    }

    @Basic
    @Column(name = "istop", nullable = false)
    public Short getIstop() {
        return istop;
    }

    public void setIstop(Short istop) {
        this.istop = istop;
    }

    @Basic
    @Column(name = "message", nullable = false, length = 4000)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "pic", nullable = false)
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Basic
    @Column(name = "subtitle", nullable = false)
    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @Basic
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "tid", referencedColumnName = "tid", updatable = false, insertable = false)
    public Team getLTeams() {
        return lTeams;
    }

    public void setLTeams(Team lTeams) {
        this.lTeams = lTeams;
    }

    @Basic
    @Column(name = "tid", nullable = false)
    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
}
