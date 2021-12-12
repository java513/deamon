package com.lh.spring.event;

import com.lh.spring.dto.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

/**
 * @program: deamon
 * @description:
 * @author: lh
 * @date: 2021-12-05 11:42
 **/
@Slf4j
public class MyEvent extends ApplicationEvent {
    private Task task;
    public MyEvent(Task task) {
        super(task);
        this.task=task;
    }
    public Task getTask(){
        return task;
    }
}
