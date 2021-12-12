package com.lh.spring.event;

import com.lh.AppSpringBoot;
import com.lh.spring.dto.Task;
import com.lh.spring.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: deamon
 * @description:
 * https://blog.csdn.net/qq330983778/article/details/99762511?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.no_search_link&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.no_search_link
 * @author: lh
 * @date: 2021-12-05 11:50
 **/
@SpringBootTest(classes = AppSpringBoot.class)
@RunWith(SpringRunner.class)
@Slf4j
public class EventTest {
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    OrderService orderService;

    @Test
    public void publishTest(){
        Task task = new Task();
        task.setId(1L);
        task.setTaskName("测试任务");
        task.setTaskContext("测试内容");
        task.setFinish(false);
        MyEvent myEvent = new MyEvent(task);
        log.info("开始发布任务～～");
        publisher.publishEvent(myEvent);
        log.info("发布 任务结束～～");
    }

    @Test
    public void orderCreateTest(){
        orderService.save();
    }

    /**
     * protected void publishEvent(Object event, @Nullable ResolvableType eventType) {
     * 		Assert.notNull(event, "Event must not be null");
     * 		// 无关内容
     * 		if (logger.isTraceEnabled()) {
     * 			logger.trace("Publishing event in " + getDisplayName() + ": " + event);
     *                }
     *
     * 		// 类型转换
     * 		ApplicationEvent applicationEvent;
     * 		if (event instanceof ApplicationEvent) {
     * 			applicationEvent = (ApplicationEvent) event;
     *        }
     * 		else {
     * 			applicationEvent = new PayloadApplicationEvent<>(this, event);
     * 			if (eventType == null) {
     * 				eventType = ((PayloadApplicationEvent) applicationEvent).getResolvableType();
     *            }
     *        }
     *
     * 		// 在早期事件，容器初始化时候使用，可以忽略
     * 		if (this.earlyApplicationEvents != null) {
     * 			this.earlyApplicationEvents.add(applicationEvent);
     *        }
     * 		else {
     * 		    // ② 进行任务广播的主要逻辑
     * 			getApplicationEventMulticaster().multicastEvent(applicationEvent, eventType);
     *        }
     *
     * 		// 方便使用父类进行发布事件，非重点
     * 		if (this.parent != null) {
     * 			if (this.parent instanceof AbstractApplicationContext) {
     * 				((AbstractApplicationContext) this.parent).publishEvent(event, eventType);
     *            }
     * 			else {
     * 				this.parent.publishEvent(event);
     *            }
     *        }* 	}
     *
     */
}
