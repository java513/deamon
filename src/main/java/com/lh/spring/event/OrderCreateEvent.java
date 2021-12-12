package com.lh.spring.event;

import com.lh.spring.dto.Order;
import org.springframework.context.ApplicationEvent;

/**
 * @program: deamon
 * @description: https://www.cnblogs.com/wuzhenzhao/p/12859876.html
 * @author: lh
 * @date: 2021-12-05 12:09
 **/
//源事件
public class OrderCreateEvent extends ApplicationEvent {
    private final Order order;
    public OrderCreateEvent(Order order) {
        super(order);
        this.order=order;
    }
    public Order getOrder(){
        return order;
    }
}
