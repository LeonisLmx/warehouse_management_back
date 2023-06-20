package cn.pqz.emsboot.modules.output.service;

import cn.pqz.emsboot.component.util.OrderNumUtil;
import cn.pqz.emsboot.modules.output.entity.Client_order;
import cn.pqz.emsboot.modules.output.entity.OrderList;
import cn.pqz.emsboot.modules.output.http.OrderRequest;
import cn.pqz.emsboot.modules.output.mapper.Client_orderMapper;
import cn.pqz.emsboot.modules.output.mapper.OrderListMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderListService extends ServiceImpl<OrderListMapper,OrderList> {
    @Autowired
    private OrderListMapper orderListMapper;
    @Autowired
    private Client_orderMapper client_orderMapper;
    /**
     * 订单分页查询
     */
    public List<OrderList> orderList(Integer pageNum, Integer size, String query, String orderNumber, Integer orderState) {
        QueryWrapper<OrderList> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(query)) {
            queryWrapper.like("name", query);
        }
        if (StringUtils.isNotBlank(orderNumber)){
            queryWrapper.eq("orderNum", orderNumber);
        }
        if (orderState != null && orderState > 0){
            if (orderState == 1){
                queryWrapper.gt("orderState",1);
            }else{
                queryWrapper.eq("orderState",0);
            }
        }
        IPage<OrderList> page = orderListMapper.selectPage(new Page<>(pageNum, size), queryWrapper);
        return page.getRecords();
    }
    /**
     * 添加订单
     * 新建时间，生成订单号，关联客户
     */
    public void addOrder(OrderRequest orderRequest){
        OrderList order=new OrderList();
        BeanUtils.copyProperties(orderRequest, order);
        order.setDate(new Date());
        order.setOrderNum(OrderNumUtil.GetRandom());
        order.setPay(false);
        order.setTransport(false);
        order.setOrderState(1);
        orderListMapper.insert(order);
        Client_order co = new Client_order();
        co.setCid(orderRequest.getClientId().intValue());
        co.setOid(order.getId());
        client_orderMapper.insert(co);
    }
}
