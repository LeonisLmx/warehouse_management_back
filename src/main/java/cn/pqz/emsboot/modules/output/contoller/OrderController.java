package cn.pqz.emsboot.modules.output.contoller;

import cn.pqz.emsboot.component.util.OrderStateEnum;
import cn.pqz.emsboot.modules.output.entity.OrderList;
import cn.pqz.emsboot.modules.output.http.OrderRequest;
import cn.pqz.emsboot.modules.output.service.OrderListService;
import cn.pqz.emsboot.modules.sys.entity.RespBean;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderListService orderListService;

    /**
     * 获取订单列表
     * @param pageNum
     * @param size
     * @param query
     * @return
     */
    @GetMapping("/orderList/")
    public RespBean orderList(@RequestParam("pageNum") Integer pageNum,
                              @RequestParam("size") Integer size,
                              @RequestParam(value = "query",required = false) String query,
                              @RequestParam(value = "orderNumber", required = false)String orderNumber,
                              @RequestParam(value = "orderState", required = false) String orderState,
                              @RequestParam(value = "orderType", required = false) String orderType){
        return RespBean.ok("", orderListService.orderList(pageNum, size, query, orderNumber, orderState, orderType));
    }

    @GetMapping("/list/")
    public RespBean orderList(@RequestParam("pageNum") Integer pageNum,
                              @RequestParam("size") Integer size,
                              @RequestParam(value = "startTime",required = false) Long startTime,
                              @RequestParam(value = "endTime", required = false) Long endTime,
                              @RequestParam(value = "clientId", required = false) Long clientId,
                              @RequestParam(value = "orderType", required = false) Integer orderType,
                              @RequestParam(value = "operateId", required = false) Long operateId,
                              @RequestParam(value = "orderState", required = false) Integer orderState,
                              @RequestParam(value = "expressName", required = false) String expressName){
        return RespBean.ok("", orderListService.orderList(
                pageNum,size,startTime,endTime, clientId, orderType, operateId, orderState, expressName));
    }

    /**
     * 新建生产远程搜索
     * @param name
     * @return
     */
    @GetMapping("/orders")
    public RespBean orders(@RequestParam String name){
        RespBean respBean=null;
        try{QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("orderState",1);
        queryWrapper.like("name",name);
        List<OrderList> orders= orderListService.list(queryWrapper);
        respBean=RespBean.ok("",orders);
        }catch (Exception e){
            e.printStackTrace();
            respBean=RespBean.error("获取失败");
        }
        return respBean;
    }
    @PostMapping("/addOrder")
    public RespBean addOrder(@RequestBody OrderRequest orderRequest){
        RespBean respBean=null;
        try{
            orderListService.addOrder(orderRequest);
            respBean=RespBean.ok("新建订单成功");
        }catch (Exception e){
            e.printStackTrace();
            respBean=RespBean.error("新建订单失败");
        }
        return respBean;
    }

    /**
     * 修改订单
     * @param order
     * @return
     */
    @PutMapping("/editOrder")
    public RespBean editOrder(@RequestBody OrderList order){
        RespBean respBean=null;
        Boolean i= orderListService.updateById(order);
        if (i){
            respBean=RespBean.ok("修改成功");
        }else {
            respBean=RespBean.error("修改失败");
        }
        return respBean;
    }
    @DeleteMapping("/deleteOrder/{id}")
    public RespBean deleteOrder(@PathVariable("id") Integer id){
        RespBean respBean=null;
        Boolean i= orderListService.removeById(id);
        if (!i){
            respBean=RespBean.error("删除失败");
        }else {
            respBean=RespBean.ok("删除成功");
        }
        return respBean;
    }

    @GetMapping("/aggregation/data")
    public RespBean aggregationData(@RequestParam(value = "startTime", required = false)Long startTime,
                                    @RequestParam(value = "endTime", required = false)Long endTime,
                                    @RequestParam(value = "operateId", required = false)Long operateId){
        List<Map<String, Object>> map = orderListService.aggregateData(startTime, endTime, operateId);
        log.info("mapper result is {}",JSONObject.toJSONString(map));
        return RespBean.ok("", map);
    }

    @GetMapping("/searchByOrderId")
    public RespBean searchByOrderId(@RequestParam("orderId")String orderId){
        return RespBean.ok("", orderListService.searchByOrderId(orderId));
    }

    /**
     * 修改订单状态
     * @param orderList
     * @return
     */
    @PostMapping("/stateUpdate")
    public RespBean orderStateUpdate(@RequestBody OrderList orderList){
        return RespBean.ok("修改订单状态成功", orderListService.updateOrderState(
                OrderStateEnum.parseState(orderList.getOrderState()), orderList.getOrderNum()));
    }

    /**
     * 分配站点
     * @param jsonObject
     * @return
     */
    @PostMapping("/selectSubstation")
    public RespBean selectSubstation(@RequestBody JSONObject jsonObject){
        return RespBean.ok("分配站点成功", orderListService.updateSubstation(
                jsonObject.getLong("substationId"),jsonObject.getString("orderNum")));
    }

    /**
     * 选择快递员
     * @param jsonObject
     * @return
     */
    @PostMapping("/selectExpress")
    public RespBean selectExpress(@RequestBody JSONObject jsonObject){
        return RespBean.ok("分配快递员成功", orderListService.updateExpress(
                jsonObject.getString("expressName"),jsonObject.getString("orderNum")));
    }

    /**
     * 回执录入
     * @param jsonObject
     * @return
     */
    @PostMapping("/receipt")
    public RespBean receipt(@RequestBody JSONObject jsonObject){
        return RespBean.ok("回执录入成功", orderListService.updateCustomerSatisfaction(
                jsonObject.getLong("customerSatisfaction"),
                jsonObject.getInteger("code"),
                jsonObject.getString("orderNum")));
    }

    /**
     * 分站订单数据统计
     * @return
     */
    @GetMapping("/staticsSubstationOrders")
    public RespBean staticsSubstationOrders() {
        return RespBean.ok("", orderListService.staticsOrders());
    }

    /**
     * 财务数据整合-供应商结算
     * @param goodsId
     * @return
     */
    @GetMapping("/integrateData")
    public RespBean integrateData(Long goodsId){
        return RespBean.ok("", orderListService.integrateData(goodsId));
    }
}
