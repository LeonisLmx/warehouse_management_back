package cn.pqz.emsboot.config.schedule;

import cn.pqz.emsboot.component.util.OrderStateEnum;
import cn.pqz.emsboot.modules.output.entity.OrderList;
import cn.pqz.emsboot.modules.output.service.OrderListService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author urey.liu
 * @description 每5分钟执行一次，自动将NEW_ORDER -> ORDER_SCHEDULE
 * @date 2023/6/25 4:03 下午
 */
@Component
public class OrderSchedule {

    @Resource
    private OrderListService orderListService;

    @Scheduled(initialDelay = 100,fixedDelay = 300)
    public void scheduleTask(){
        IPage<OrderList> orderListIPage = orderListService.orderList(1, 100, null, null, OrderStateEnum.NEW_ORDER.getCode(), null);
        for (OrderList record : orderListIPage.getRecords()) {
            orderListService.updateOrderState(OrderStateEnum.ORDER_SCHEDULE, record.getOrderNum());
        }
    }
}
