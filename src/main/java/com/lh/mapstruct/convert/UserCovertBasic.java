package com.lh.mapstruct.convert;

import com.lh.mapstruct.vo.User;
import com.lh.mapstruct.vo.UserVO1;
import com.lh.mapstruct.vo.UserVO2;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-10-23 17:52
 **/
@Mapper(componentModel = "spring")
public interface UserCovertBasic {
    UserCovertBasic INSTANCE = Mappers.getMapper(UserCovertBasic.class);

    /**
     * 字段数量类型数量相同，利用工具BeanUtils也可以实现类似效果
     * @param source
     * @return
     */
    UserVO1 toConvertVO1(User source);

    User fromConvertEntity(UserVO1 userVO1);

    /**
     *  字段数量类型相同,数量少：仅能让多的转换成少的，故没有fromConvertEntity2
     * @param source
     * @return
     */
    UserVO2 toConvertVO2(User source);

}
