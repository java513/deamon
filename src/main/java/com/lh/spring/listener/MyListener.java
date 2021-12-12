package com.lh.spring.listener;

import com.alibaba.fastjson.JSON;
import com.lh.spring.dto.Task;
import com.lh.spring.event.MyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-12-05 11:44
 **/
@Component
@Slf4j
public class MyListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        Task task = myEvent.getTask();
        log.info("接受到事件 {}",JSON.toJSONString(myEvent));
        task.setFinish(true);
        log.info("事件处理结束～～");
    }
}
