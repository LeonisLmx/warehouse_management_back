package cn.pqz.emsboot.config.schedule;

import cn.pqz.emsboot.component.util.OrderStateEnum;
import cn.pqz.emsboot.modules.output.entity.OrderList;
import cn.pqz.emsboot.modules.output.service.OrderListService;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
    @Scheduled(initialDelay = 0,fixedDelay = 300_000)
    public void scheduleOrderStateTaskNewOrder(){
        log.info("start schedule task by order state ORDER_TRANSPORT to target state ORDER_SCHEDULE");
        IPage<OrderList> orderListIPage = orderListService.orderList(1, 100, null, null, OrderStateEnum.NEW_ORDER.getCode(), null);
        for (OrderList record : orderListIPage.getRecords()) {
            orderListService.updateOrderState(OrderStateEnum.ORDER_SCHEDULE, record.getOrderNum());
        }
    }

    /**
     * ORDER_WAREHOUSE -> ORDER_TRANSPORT
     */
    @Scheduled(initialDelay = 60_000,fixedDelay = 300_000)
    public void scheduleOrderStateTaskOrderWarehouse(){
        log.info("start schedule task by order state ORDER_TRANSPORT to target state ORDER_TRANSPORT");
        IPage<OrderList> orderListIPage = orderListService.orderList(1, 100, null, null, OrderStateEnum.ORDER_WAREHOUSE.getCode(), null);
        for (OrderList record : orderListIPage.getRecords()) {
            orderListService.updateOrderState(OrderStateEnum.ORDER_TRANSPORT, record.getOrderNum());
        }
    }

    /**
     * ORDER_TRANSPORT -> GOOD_IN_STORAGE
     */
    @Scheduled(initialDelay = 120_000,fixedDelay = 300_000)
    public void scheduleOrderStateTaskOrderTransport(){
        log.info("start schedule task by order state ORDER_TRANSPORT to target state GOOD_IN_STORAGE");
        IPage<OrderList> orderListIPage = orderListService.orderList(1, 100, null, null, OrderStateEnum.ORDER_TRANSPORT.getCode(), null);
        for (OrderList record : orderListIPage.getRecords()) {
            orderListService.updateOrderState(OrderStateEnum.GOOD_IN_STORAGE, record.getOrderNum());
        }
    }

    /**
     * GOOD_IN_STORAGE -> ORDER_WAIT_ALLOCATION
     */
    @Scheduled(initialDelay = 180_000,fixedDelay = 300_000)
    public void scheduleOrderStateTaskGoodInStorage(){
        log.info("start schedule task by order state GOOD_IN_STORAGE to target state ORDER_WAIT_ALLOCATION");
        IPage<OrderList> orderListIPage = orderListService.orderList(1, 100, null, null, OrderStateEnum.GOOD_IN_STORAGE.getCode(), null);
        for (OrderList record : orderListIPage.getRecords()) {
            orderListService.updateOrderState(OrderStateEnum.ORDER_WAIT_ALLOCATION, record.getOrderNum());
        }
    }


    /**
     * ALLOCATION_OUT_STORAGE -> DELIVERY_GOOD
     */
    @Scheduled(initialDelay = 240_000,fixedDelay = 300_000)
    public void scheduleOrderStateTaskAllocationOutStorage(){
        log.info("start schedule task by order state ALLOCATION_OUT_STORAGE to target state DELIVERY_GOOD");
        IPage<OrderList> orderListIPage = orderListService.orderList(1, 100, null, null, OrderStateEnum.ALLOCATION_OUT_STORAGE.getCode(), null);
        for (OrderList record : orderListIPage.getRecords()) {
            orderListService.updateOrderState(OrderStateEnum.DELIVERY_GOOD, record.getOrderNum());
        }
    }
}
