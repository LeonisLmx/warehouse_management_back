package cn.pqz.emsboot.modules.output.contoller;

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
                              @RequestParam(value = "orderState", required = false) Integer orderState,
                              @RequestParam(value = "orderType", required = false) Integer orderType){
        JSONObject obj = new JSONObject();
        IPage<OrderList> orderListIPage = orderListService.orderList(pageNum, size, query, orderNumber, orderState, orderType);
        obj.put("data", orderListIPage.getRecords());
        obj.put("total", orderListIPage.getTotal());
        return RespBean.ok("",obj);
    }

    @GetMapping("/list/")
    public RespBean orderList(@RequestParam("pageNum") Integer pageNum,
                              @RequestParam("size") Integer size,
                              @RequestParam(value = "startTime",required = false) Long startTime,
                              @RequestParam(value = "endTime", required = false) Long endTime,
                              @RequestParam(value = "clientId", required = false) Long clientId,
                              @RequestParam(value = "orderType", required = false) Integer orderType,
                              @RequestParam(value = "operateId", required = false) Long operateId){
        return RespBean.ok("", orderListService.orderList(pageNum,size,startTime,endTime, clientId, orderType, operateId));
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
}
