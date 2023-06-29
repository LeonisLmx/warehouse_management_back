package cn.pqz.emsboot.modules.output.service;

import cn.pqz.emsboot.component.util.OrderNumUtil;
import cn.pqz.emsboot.component.util.OrderStateEnum;
import cn.pqz.emsboot.component.util.UserUtil;
import cn.pqz.emsboot.modules.business.service.FinanceService;
import cn.pqz.emsboot.modules.output.entity.Client;
import cn.pqz.emsboot.modules.output.entity.Client_order;
import cn.pqz.emsboot.modules.output.entity.OrderList;
import cn.pqz.emsboot.modules.output.http.OrderRequest;
import cn.pqz.emsboot.modules.output.http.OrderResponse;
import cn.pqz.emsboot.modules.output.mapper.Client_orderMapper;
import cn.pqz.emsboot.modules.output.mapper.OrderListMapper;
import cn.pqz.emsboot.modules.sys.entity.User;
import cn.pqz.emsboot.modules.sys.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class OrderListService extends ServiceImpl<OrderListMapper,OrderList> {
    @Autowired
    private OrderListMapper orderListMapper;
    @Autowired
    private Client_orderMapper client_orderMapper;
    @Resource
    private ClientService clientService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private FinanceService financeService;
    /**
     * 订单分页查询
     */
    public IPage<OrderList> orderList(Integer pageNum, Integer size,
                                     String query, String orderNumber,
                                     Integer orderState, Integer orderType) {
        return this.orderList(pageNum, size, query, orderNumber, String.valueOf(orderState), String.valueOf(orderType));
    }

    public IPage<OrderList> orderList(Integer pageNum, Integer size,
                                      String query, String orderNumber,
                                      String orderState, String orderType) {
        QueryWrapper<OrderList> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(query)) {
            queryWrapper.like("name", query);
        }
        if (StringUtils.isNotBlank(orderNumber)){
            queryWrapper.eq("orderNum", orderNumber);
        }
        if (StringUtils.isNotBlank(orderState)){
            queryWrapper.in("orderState", Arrays.asList(orderState.split(",")));
        }
        if (StringUtils.isNotBlank(orderType)){
            queryWrapper.in("orderType", Arrays.asList(orderType.split(",")));
        }
        return orderListMapper.selectPage(new Page<>(pageNum, size), queryWrapper);
    }

    public Map<String,Object> orderList(Integer pageNum, Integer size,
                                     Long startTime, Long endTime,
                                     Long clientId, Integer orderType,
                                     Long operateId, Integer orderState,
                                        String expressName) {
        QueryWrapper<OrderList> queryWrapper = new QueryWrapper<>();
        if (startTime != null) {
            queryWrapper.ge("date", new Date(startTime));
        }
        if (endTime != null){
            queryWrapper.le("date", new Date(endTime));
        }
        if (clientId != null){
            queryWrapper.eq("clientId", clientId);
        }
        if (orderType != null){
            queryWrapper.eq("orderType", orderType);
        }
        if (operateId != null){
            queryWrapper.eq("operateId", operateId);
        }
        if (orderState != null){
            queryWrapper.eq("orderState", orderState);
        }
        if (StringUtils.isNotBlank(expressName)){
            queryWrapper.eq("expressName", expressName);
        }
        IPage<OrderList> page = orderListMapper.selectPage(new Page<>(pageNum, size), queryWrapper);
        List<OrderResponse> list = new ArrayList<>();
        for (OrderList record : page.getRecords()) {
            OrderResponse orderResponse = new OrderResponse();
            BeanUtils.copyProperties(record, orderResponse);
            Client client = clientService.getClientById(orderResponse.getClientId());
            if (client != null) {
                orderResponse.setClientName(client.getName());
                orderResponse.setPhone(client.getPhone());
                orderResponse.setAddress(client.getAddress());
            }
            User user = userMapper.selectById(record.getOperateId());
            if (user != null) {
                orderResponse.setOperateName(user.getName());
            }
            list.add(orderResponse);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("data",list);
        map.put("total",page.getTotal());
        return map;
    }
    /**
     * 添加订单
     * 新建时间，生成订单号，关联客户
     */
    public void addOrder(OrderRequest orderRequest){
        OrderList order=new OrderList();
        BeanUtils.copyProperties(orderRequest, order);
        order.setDate(new Date(orderRequest.getTime()));
        order.setOrderNum(OrderNumUtil.GetRandom());
        order.setPay(false);
        order.setTransport(false);
        if (orderRequest.isChecked()){
            order.setOrderState(OrderStateEnum.LOSS_GOOD.getCode());
            // 和供应商添加财务关系
            financeService.addRecord(3, orderRequest.getPrice().longValue(),-1);
        }else {
            order.setOrderState(orderRequest.getOrderState() == null ? OrderStateEnum.NEW_ORDER.getCode() : order.getOrderState());
        }
        financeService.addRecord(1, orderRequest.getPrice().longValue(),1);
        User user = UserUtil.getCurrentUser();
        order.setOperateId(user.getId());
        orderListMapper.insert(order);
        Client_order co = new Client_order();
        co.setCid(orderRequest.getClientId().intValue());
        co.setOid(order.getId());
        client_orderMapper.insert(co);
    }

    public List<Map<String,Object>> aggregateData(Long startTime, Long endTime, Long operateId){
        QueryWrapper<OrderList> queryWrapper = new QueryWrapper<>();
        if (startTime != null){
            queryWrapper.ge("date",startTime);
        }
        if (endTime != null){
            queryWrapper.le("date",endTime);
        }
        return orderListMapper.aggregateData(startTime == null ? null : new Date(startTime),
                endTime == null ? null : new Date(endTime), operateId);
    }

    public Map<String,Object> searchByOrderId(String orderId){
        QueryWrapper<OrderList> queryWrapper = new QueryWrapper<OrderList>().eq("orderNum",orderId);
        Map<String,Object> map = new HashMap<>();
        OrderList orderList = orderListMapper.selectOne(queryWrapper);
        if (orderList != null){
            Client client = clientService.getClientById(orderList.getClientId());
            map.put("order",orderList);
            map.put("client",client);
        }
        return map;
    }

    public int updateOrderState(OrderStateEnum orderStateEnum, String orderNum){
        OrderList orderList = new OrderList();
        orderList.setOrderState(orderStateEnum.getCode());
        return orderListMapper.update(orderList, new QueryWrapper<OrderList>().eq("orderNum",orderNum));
    }

    public int updateSubstation(Long substationId, String orderNum){
        OrderList orderList = new OrderList();
        orderList.setSubstationId(substationId);
        orderList.setOrderState(OrderStateEnum.ORDER_WAREHOUSE.getCode());
        return orderListMapper.update(orderList, new QueryWrapper<OrderList>().eq("orderNum",orderNum));
    }

    public int updateExpress(String expressName, String orderNum){
        OrderList orderList = new OrderList();
        orderList.setExpressName(expressName);
        orderList.setOrderState(OrderStateEnum.ALLOCATION_OUT_STORAGE.getCode());
        return orderListMapper.update(orderList, new QueryWrapper<OrderList>().eq("orderNum",orderNum));
    }

    public int updateCustomerSatisfaction(Long customerSatisfaction,Integer code, String orderNum){
        OrderList orderList = new OrderList();
        orderList.setCustomerSatisfaction(customerSatisfaction);
        orderList.setOrderState(code);
        return orderListMapper.update(orderList, new QueryWrapper<OrderList>().eq("orderNum",orderNum));
    }

    public List<OrderList> queryInvoiceList(){
        QueryWrapper<OrderList> queryWrapper = new QueryWrapper<OrderList>().eq("invoiceEnabled",true);
        return orderListMapper.selectList(queryWrapper);
    }
}
