package com.lh.mapstruct.convert;

import com.lh.mapstruct.dto.CarDTO;
import com.lh.mapstruct.dto.DriverDTO;
import com.lh.mapstruct.dto.PartDTO;
import com.lh.mapstruct.vo.CarVO;
import com.lh.mapstruct.vo.DriverVO;
import com.lh.mapstruct.vo.VehicleVO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 使用mapstruct步骤
 * 1.引入依赖
 * 2.新建一个抽象类或者接口并标注@Mapper
 * 3.写一个转换方法
 * 4.获取对象INSTANCE并转化
 *
 * 默认映射规则
 * 同类型且同名的属性，会自动映射
 *
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-10-24 11:57
 **/
@Mapper
public abstract class CarConvert {

    public static CarConvert INSTANCE = Mappers.getMapper(CarConvert.class);
    /**
     * carDTO -> CarVO
     * @param carDTO
     * @return
     */
    @Mappings({
            @Mapping(source = "totalPrice",target = "totalPrice",numberFormat = "#.00"),
            @Mapping(source = "publishDate",target = "publishDate",dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "color",ignore = true),
            @Mapping(source = "brand",target = "brandName"),
            @Mapping(source = "driverDTO",target = "driverVO")
    })
    public abstract CarVO carDTO2VO(CarDTO carDTO);

    /**
     * driverDTO -> DriverVO
     * @param driverDTO
     * @return
     */
    @Mapping(source = "id",target = "driverId")
    @Mapping(source = "name",target = "fullName")
    public abstract DriverVO driverDTO2VO(DriverDTO driverDTO);

    /**
     * @AfterMapping + @MappingTarget 配合在属性最后一步完成对属性对自定义映射
     * @param carDTO
     * @param carVO
     */
    @AfterMapping
    public void carDTO2VOAfter(CarDTO carDTO, @MappingTarget CarVO carVO) {
        List<PartDTO> partDTOS = carDTO.getPartDTOS();
        boolean hasPart = partDTOS != null && !partDTOS.isEmpty();
        carVO.setHasPart(hasPart);
    }

    /**
     * 批量转换
     * @param carDTOs
     * @return
     */
    public abstract List<CarVO> carDTOs2VOs(List<CarDTO> carDTOs);

    /**
     * @BeanMapping ignoreByDefault忽略mapstruct默认映射行为
     * @param carDTO
     * @return
     */
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id",target = "id")
    public abstract VehicleVO carDTO2vehicleVO(CarDTO carDTO);
}
