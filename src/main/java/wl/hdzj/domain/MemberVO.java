package wl.hdzj.domain;

import wl.hdzj.entity.Team;

import java.util.List;

/**
 * Member 领域模型
 */
public class MemberVO{
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

    //当作为输出模型时传出
    private List<Team> rTeams;

    @Override
    public String toString() {
        return "MemberVO{" +
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

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Short getIdentify() {
        return identify;
    }

    public void setIdentify(Short identify) {
        this.identify = identify;
    }

    public Short getIsshow() {
        return isshow;
    }

    public void setIsshow(Short isshow) {
        this.isshow = isshow;
    }

    public String getXueyuan() {
        return xueyuan;
    }

    public void setXueyuan(String xueyuan) {
        this.xueyuan = xueyuan;
    }

    public List<Team> getRTeams() {
        return rTeams;
    }

    public void setRTeams(List<Team> rTeams) {
        this.rTeams = rTeams;
    }
}
