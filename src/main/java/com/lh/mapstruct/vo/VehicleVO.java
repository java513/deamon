package com.lh.mapstruct.vo;

import lombok.Data;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-10-24 12:46
 **/
@Data
public class VehicleVO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 裸车的价格
     */
    private Double price;
}
