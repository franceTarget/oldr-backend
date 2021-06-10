package com.ren.oldr.models.request;

import lombok.Data;

/**
 * @ClassName PageReq
 * @Description TODO
 * @Author canove
 * @Date 2020/10/9 9:31
 * @Version 1.0
 */
@Data
public class PageReq {

    private Integer page = 1;

    private Integer pageSize = 10;
}
