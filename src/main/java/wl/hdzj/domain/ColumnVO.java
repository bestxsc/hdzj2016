package wl.hdzj.domain;

public class ColumnVO {
    private Integer cid;
    private String name;
    private Integer parterid;

    @Override
    public String toString() {
        return "ColumnVO{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", parterid=" + parterid +
                '}';
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParterid() {
        return parterid;
    }

    public void setParterid(Integer parterid) {
        this.parterid = parterid;
    }
}
