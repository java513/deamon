package com.lh.spring.service;

import com.lh.spring.dto.Order;
import com.lh.spring.event.OrderCreateEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @program: deamon
 * @description: 事件发布服务
 * @author: lh
 * @date: 2021-12-05 12:16
 **/
@Service
public class OrderService implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher=applicationEventPublisher;
    }

    /**
     * 订单保存
     */
    public void save(){
        Order order = new Order();
        order.setOrderNo("1");
        order.setGoods("手机");
        publisher.publishEvent(new OrderCreateEvent(order));
    }
}
