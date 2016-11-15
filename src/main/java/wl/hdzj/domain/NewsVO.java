package wl.hdzj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import wl.hdzj.entity.Columnnn;
import wl.hdzj.entity.Team;

/**
 * 新闻 - 领域模型
 * @author: lipengbiao
 */
public class NewsVO{
    private Integer nid;

    /*
    当作为输入模型时传入
    在实体类中并没有这两个属性，当领域模型与实体类进行转换的时候
    这两个属性并不会传进去
    */
    @JsonIgnore
    private Integer tid;
    @JsonIgnore
    private Integer cid;

    /*
    当作为输出模型时传出
    或是传入tid、cid时转换为持久实体填入下列属性，然后领域模型再转换为
    实体进行其他操作
     */
    private Columnnn lColumn;
    private Team lTeams;
    /*
    也就是说，这两组属性并不会造成污染
     */
    public Columnnn getLColumn() {
        return lColumn;
    }

    public void setLColumn(Columnnn lColumn) {
        this.lColumn = lColumn;
    }

    public Team getLTeams() {
        return lTeams;
    }

    public void setLTeams(Team lTeams) {
        this.lTeams = lTeams;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "NewsVO{" +
                "nid=" + nid +
                ", tid=" + tid +
                ", cid=" + cid +
                ", lColumn=" + lColumn +
                ", lTeams=" + lTeams +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", message='" + message + '\'' +
                ", auther='" + auther + '\'' +
                ", pic='" + pic + '\'' +
                ", type=" + type +
                ", isdraft=" + isdraft +
                ", istop=" + istop +
                '}';
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    //@NotNull(message = "标题不能为空")
    @Length(min = 5, max = 500, message = "标题长度范围为5-500位字符")
    private String title;

    private String subtitle;

    //@NotNull(message = "内容不能为空")
    @Length(min = 5, max = 4000, message = "标题长度范围为5-4000位字符")
    private String message;

    @Length(min = 5, max = 500, message = "标题长度范围为5-500位字符")
    private String auther;

    @Length(min = 5, max = 500, message = "标题长度范围为5-500位字符")
    private String pic;

    private Short type;

    @Range(min = 0, max = 1, message = "是否为草稿")
    private Short isdraft;

    @Range(min = 0, max = 1, message = "是否置顶")
    private Short istop;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Short getIsdraft() {
        return isdraft;
    }

    public void setIsdraft(Short isdraft) {
        this.isdraft = isdraft;
    }

    public Short getIstop() {
        return istop;
    }

    public void setIstop(Short istop) {
        this.istop = istop;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }
    public Integer getNid() { return this.nid; }

}