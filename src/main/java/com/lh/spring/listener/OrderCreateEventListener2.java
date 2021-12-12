package com.lh.spring.listener;

import com.alibaba.fastjson.JSON;
import com.lh.spring.dto.Order;
import com.lh.spring.event.OrderCreateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-12-05 12:26
 **/
@Component
@Slf4j
public class OrderCreateEventListener2 {
    @EventListener
    public void onApplicationEvent(OrderCreateEvent event) {
        Order order = event.getOrder();
        log.info("监听到基于注解保存订单事件 {}", JSON.toJSONString(order));
    }
}
