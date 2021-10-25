package com.lh.mapstruct;

import com.lh.AppSpringBoot;
import com.lh.mapstruct.convert.CarConvert;
import com.lh.mapstruct.dto.CarDTO;
import com.lh.mapstruct.dto.DriverDTO;
import com.lh.mapstruct.dto.PartDTO;
import com.lh.mapstruct.vo.CarVO;
import com.lh.mapstruct.vo.DriverVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-10-24 11:29
 **/
@SpringBootTest(classes = AppSpringBoot.class)
@RunWith(SpringRunner.class)
public class MapstrcutTest {

    @Test
    public void carDTO2vehicleVO() {
        CarDTO carDTO = buildCarDTO();
        System.out.println(CarConvert.INSTANCE.carDTO2vehicleVO(carDTO));
    }

    @Test
    public void carDTOs2VOs(){
        CarDTO carDTO = buildCarDTO();
        ArrayList<CarDTO> carDTOS = new ArrayList<>();
        carDTOS.add(carDTO);

        List<CarVO> carVOS = CarConvert.INSTANCE.carDTOs2VOs(carDTOS);
        System.out.println(carVOS);
    }

    @Test
    public void carDTO2VO() {
        CarDTO carDTO = buildCarDTO();
        CarVO carVO = CarConvert.INSTANCE.carDTO2VO(carDTO);
        System.out.println(carVO);
    }

    //传统set方式
    @Test
    public void traditionConvert(){
        CarDTO carDTO = buildCarDTO();
        CarVO carVO = new CarVO();
        carVO.setId(carDTO.getId());
        carVO.setVin(carDTO.getVin());
        carVO.setPrice(carDTO.getPrice()); //自动拆箱装箱机制

        double totalPrice = carDTO.getTotalPrice();
        DecimalFormat format = new DecimalFormat("#.00");
        String totalPriceStr = format.format(totalPrice);
        carVO.setTotalPrice(totalPriceStr);

        Date publishDate = carDTO.getPublishDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String publishDateStr = simpleDateFormat.format(publishDate);
        carVO.setPublishDate(publishDateStr);

        carVO.setBrandName(carDTO.getBrand());

        List<PartDTO> partDTOS = carDTO.getPartDTOS();
        boolean hasPart = partDTOS != null && !partDTOS.isEmpty();
        carVO.setHasPart(hasPart);

        DriverVO driverVO = new DriverVO();
        DriverDTO driverDTO = carDTO.getDriverDTO();
        driverVO.setDriverId(driverDTO.getId());
        driverVO.setFullName(driverDTO.getName());
        carVO.setDriverVO(driverVO);

        System.out.println(carVO);

    }

    private CarDTO buildCarDTO() {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(330L);
        carDTO.setVin("vin123456789");
        carDTO.setPrice(123789.126d);
        carDTO.setTotalPrice(143789.126d);
        carDTO.setPublishDate(new Date());
        carDTO.setBrand("大众");
        carDTO.setColor("白色");
        //零件
        PartDTO partDTO1 = new PartDTO();
        partDTO1.setPartId(1L);
        partDTO1.setPartName("多功能方向盘");
        PartDTO partDTO2 = new PartDTO();
        partDTO2.setPartId(2L);
        partDTO2.setPartName("智能门锁");
        ArrayList<PartDTO> partDTOS = new ArrayList<>();
        partDTOS.add(partDTO1);
        partDTOS.add(partDTO2);
        carDTO.setPartDTOS(partDTOS);
        //设置驾驶员
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(88L);
        driverDTO.setName("小明");
        carDTO.setDriverDTO(driverDTO);
        return carDTO;
    }
}
