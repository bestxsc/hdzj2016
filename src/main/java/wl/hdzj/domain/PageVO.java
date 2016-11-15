package wl.hdzj.domain;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 分页领域模型类
 */
public class PageVO{
    //单页数量
    @NotNull(message = "单页数量不能为空")
    @Range(min = 1, max = 100, message = "单页数量为1-100之间")
    private Integer size;
    //页标
    @NotNull(message = "页标不能为空")
    @Range(min = 0, message = "页标不能为负数")
    private Integer page;
    //排序方法
    private Byte sort;
    //排序参考
    private String sortc;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    public String getSortc() {
        return sortc;
    }

    public void setSortc(String sortc) {
        this.sortc = sortc;
    }

    PageVO(){
        super();
    }

    public PageVO(Integer size, Integer page) {
        this.size = size;
        this.page = page;
        this.sort = sort;
        this.sortc = sortc;
    }

    public PageVO(Integer size, Integer page, Byte sort, String sortc) {
        this.size = size;
        this.page = page;
        this.sort = sort;
        this.sortc = sortc;
    }
}
