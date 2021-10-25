package com.lh.mapstruct.vo;

import lombok.Data;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-10-24 11:26
 **/
@Data
public class PartVO {
    /**
     * 汽车零件ID
     */
    private Long partId;
    /**
     * 汽车零件名字
     */
    private String partName;
}
