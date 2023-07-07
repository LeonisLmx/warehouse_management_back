package cn.pqz.emsboot.config.schedule;

import cn.pqz.emsboot.component.util.OrderStateEnum;
import cn.pqz.emsboot.modules.output.service.OrderListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author urey.liu
 * @description 每5分钟执行一次，自动将NEW_ORDER -> ORDER_SCHEDULE
 * @date 2023/6/25 4:03 下午
 */
@Component
@Slf4j
public class OrderSchedule {

    @Resource
    private OrderListService orderListService;

    /**
     * NEW_ORDER -> ORDER_SCHEDULE
     */
    @Scheduled(initialDelay = 0,fixedDelay = 10_000)
    public void scheduleOrderStateTaskNewOrder(){
        log.info("start schedule task by order state NEW_ORDER to target state ORDER_SCHEDULE");
        orderListService.stateChange(OrderStateEnum.NEW_ORDER, OrderStateEnum.ORDER_SCHEDULE);
    }

    /**
     * ORDER_TRANSPORT -> GOOD_IN_STORAGE
     */
    @Scheduled(initialDelay = 120_000,fixedDelay = 10_000)
    public void scheduleOrderStateTaskOrderTransport(){
        log.info("start schedule task by order state ORDER_TRANSPORT to target state GOOD_IN_STORAGE");
        orderListService.stateChange(OrderStateEnum.ORDER_TRANSPORT, OrderStateEnum.GOOD_IN_STORAGE);
    }

    /**
     * GOOD_IN_STORAGE -> ORDER_WAIT_ALLOCATION
     */
    @Scheduled(initialDelay = 180_000,fixedDelay = 10_000)
    public void scheduleOrderStateTaskGoodInStorage(){
        log.info("start schedule task by order state GOOD_IN_STORAGE to target state ORDER_WAIT_ALLOCATION");
        orderListService.stateChange(OrderStateEnum.GOOD_IN_STORAGE, OrderStateEnum.ORDER_WAIT_ALLOCATION);
    }


    /**
     * ALLOCATION_OUT_STORAGE -> DELIVERY_GOOD
     */
    @Scheduled(initialDelay = 240_000,fixedDelay = 10_000)
    public void scheduleOrderStateTaskAllocationOutStorage(){
        log.info("start schedule task by order state ALLOCATION_OUT_STORAGE to target state DELIVERY_GOOD");
        orderListService.stateChange(OrderStateEnum.ALLOCATION_OUT_STORAGE, OrderStateEnum.DELIVERY_GOOD);
    }
}
