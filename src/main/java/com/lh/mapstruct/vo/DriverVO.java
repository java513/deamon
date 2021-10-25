package com.lh.mapstruct.vo;

import lombok.Data;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-10-24 11:24
 **/
@Data
public class DriverVO {
    private Long driverId;
    /**
     * 驾驶员名字
     */
    private String fullName;
}
