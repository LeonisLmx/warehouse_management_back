package cn.pqz.emsboot.modules.output.service;

import cn.pqz.emsboot.component.util.OrderNumUtil;
import cn.pqz.emsboot.modules.communication.WebSocketServer;
import cn.pqz.emsboot.modules.output.entity.Client_order;
import cn.pqz.emsboot.modules.output.entity.OrderList;
import cn.pqz.emsboot.modules.output.mapper.Client_orderMapper;
import cn.pqz.emsboot.modules.output.mapper.OrderMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService extends ServiceImpl<OrderMapper,OrderList> {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private Client_orderMapper client_orderMapper;
    /**
     * 订单分页查询
     */
    public List<OrderList> orderList(Integer pageNum, Integer size, String query) {
        QueryWrapper<OrderList> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", query);
        IPage<OrderList> page = orderMapper.selectPage(new Page<>(pageNum, size), queryWrapper);
        List<OrderList> orderList = page.getRecords();
        return orderList;
    }
    /**
     * 添加订单
     * 新建时间，生成订单号，关联客户
     */
    public void addOrder(String name,Double price,Integer count,Integer cid){
        OrderList order=new OrderList();
        order.setName(name);
        order.setCount(count);
        order.setPrice(price);
        Date date=new Date();
        order.setDate(date);
        String orderNum= OrderNumUtil.GetRandom();
        order.setOrderNum(orderNum);
        order.setPay(false);
        order.setTransport(false);
        order.setOrderState(1);
        orderMapper.insert(order);
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("orderNum",orderNum);
        OrderList one=orderMapper.selectOne(queryWrapper);
        Client_order co=new Client_order();
        co.setCid(cid);
        co.setOid(one.getId());
        client_orderMapper.insert(co);
    }
}
