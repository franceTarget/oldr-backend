package com.ren.oldr.models.po;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

/**
 * @ClassName PageInfo
 * @Description TODO
 * @Author canove
 * @Date 2020/9/30 17:22
 * @Version 1.0
 */
@Data
public class PageInfo<T> {

    private Integer page;

    private Integer pageSize;

    private Long total;

    private Integer pages;

    private List<T> list;

    public static PageInfo init(Page result) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(result.getPageNum());
        pageInfo.setPageSize(result.getPageSize());
        pageInfo.setPages(result.getPages());
        pageInfo.setTotal(result.getTotal());
        pageInfo.setList(result.getResult());
        return pageInfo;
    }
}
