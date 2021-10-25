package com.lh.mapstruct.dto;

import lombok.Data;

/**
 * @program: deamon
 * @description: 驾驶员
 * @author: lh
 * @date: 2021-10-24 11:20
 **/
@Data
public class DriverDTO {
    /**
     * id
     */
    private Long id;
    /**
     * 驾驶员的名字
     */
    private String name;
}
