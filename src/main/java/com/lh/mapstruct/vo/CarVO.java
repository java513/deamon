package com.lh.mapstruct.vo;

import lombok.Data;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-10-24 11:22
 **/
@Data
public class CarVO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 车辆的编号
     */
    private String vin;
    /**
     * 裸车的价格
     */
    private Double price;
    /**
     * 车的颜色
     */
    private String color;
    /**
     * 上路的价格 保留2位小数
     */
    private String totalPrice;
    /**
     * 生产日前
     */
    private String publishDate;
    /**
     * 品牌名字
     */
    private String brandName;
    /**
     * 汽车包含的零件列表
     */
    private Boolean hasPart;
    /**
     * 汽车的司机
     */
    private DriverVO driverVO;
}
