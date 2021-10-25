package com.lh.mapstruct.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-10-24 11:15
 **/
@Data
public class CarDTO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 车辆的编号
     */
    private String vin;
    /**
     * 车的颜色
     */
    private String color;
    /**
     * 裸车的价格
     */
    private double price;
    /**
     * 上路的价格
     */
    private double totalPrice;
    /**
     * 生产日前
     */
    private Date publishDate;
    /**
     * 品牌名字
     */
    private String brand;
    /**
     * 汽车包含的零件列表
     */
    private List<PartDTO> partDTOS;
    /**
     * 汽车的司机
     */
    private DriverDTO driverDTO;
}
