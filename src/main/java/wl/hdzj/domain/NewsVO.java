package wl.hdzj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import wl.hdzj.common.AddVaild;
import wl.hdzj.common.UpdateVaild;
import wl.hdzj.entity.Columnnn;
import wl.hdzj.entity.Team;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 新闻 - 领域模型
 * @author: lipengbiao
 */
public class NewsVO{
    @NotNull(message = "新闻ID不为空", groups = {UpdateVaild.class})
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

    @NotBlank(message = "参数非空", groups = {AddVaild.class})
    @Length(max = 500, message = "标题长度不超过500位字符")
    private String title;
    @NotBlank(message = "参数非空", groups = {AddVaild.class})
    @Length(max = 500, message = "子标题长度不超过500位字符")
    private String subtitle;
    @NotBlank(message = "参数非空", groups = {AddVaild.class})
    @Length(max = 4000, message = "内容长度不超过4000位字符")
    private String message;
    @NotBlank(message = "参数非空", groups = {AddVaild.class})
    @Length(max = 500, message = "作者字段长度不超过500字符")
    private String auther;
    private String pic;
    private Short type;
    @Size(max = 1, message = "isdraft字段错误")
    private Short isdraft;
    @Size(max = 1, message = "istop字段错误")
    private Short istop;

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

    public Integer getNid() {
        return this.nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

}