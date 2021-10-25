package com.lh.mapstruct.dto;

import lombok.Data;

/**
 * @program: deamon
 * @description: 汽车零件
 * @author: lh
 * @date: 2021-10-24 11:21
 **/
@Data
public class PartDTO {
    /**
     * 汽车零件ID
     */
    private Long partId;
    /**
     * 零件名字
     */
    private String partName;
}
