package cn.pqz.emsboot.modules.warehouse.service;

import cn.pqz.emsboot.component.util.OrderNumUtil;
import cn.pqz.emsboot.component.util.OrderStateEnum;
import cn.pqz.emsboot.component.util.UserUtil;
import cn.pqz.emsboot.modules.business.entity.Substation;
import cn.pqz.emsboot.modules.business.service.SubstationService;
import cn.pqz.emsboot.modules.output.entity.OrderList;
import cn.pqz.emsboot.modules.output.entity.OutputGoodsLog;
import cn.pqz.emsboot.modules.output.mapper.OrderListMapper;
import cn.pqz.emsboot.modules.output.mapper.OutputGoodsLogMapper;
import cn.pqz.emsboot.modules.output.service.OrderListService;
import cn.pqz.emsboot.modules.sys.entity.RespBean;
import cn.pqz.emsboot.modules.sys.entity.User;
import cn.pqz.emsboot.modules.warehouse.entity.Goods;
import cn.pqz.emsboot.modules.warehouse.entity.SupplierGoods;
import cn.pqz.emsboot.modules.warehouse.entity.Warehouse;
import cn.pqz.emsboot.modules.warehouse.entity.WarehouseGoods;
import cn.pqz.emsboot.modules.warehouse.mapper.GoodsMapper;
import cn.pqz.emsboot.modules.warehouse.mapper.SupplierGoodsMapper;
import cn.pqz.emsboot.modules.warehouse.mapper.WarehouseGoodsMapper;
import cn.pqz.emsboot.modules.warehouse.mapper.WarehouseMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class GoodsService extends ServiceImpl<GoodsMapper, Goods> {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private WarehouseGoodsMapper warehouseGoodsMapper;
    @Autowired
    private OrderListMapper orderListMapper;
    @Autowired
    private WarehouseMapper warehouseMapper;
    @Resource
    private OutputGoodsLogMapper outputGoodsLogMapper;
    @Resource
    private OrderListService orderListService;

    @Resource
    private SubstationService substationService;

    @Resource
    private SupplierGoodsMapper supplierGoodsMapper;

    private final Logger logger=Logger.getLogger(GoodsService.class);


    public List<Goods> enterList(Long startTime, Long endTime){
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if ( startTime != null) {
            queryWrapper.ge("date", new Date(startTime));
        }
        if (endTime != null) {
            queryWrapper.le("date", new Date(endTime));
        }
        return goodsMapper.selectList(queryWrapper);
    }

    public String[] goodsPosition(Integer gid){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("gid",gid);
        List<WarehouseGoods> wts=warehouseGoodsMapper.selectList(queryWrapper);
        String num[]=new String[wts.size()];
        int i=0;
        for (WarehouseGoods wt:wts){
            num[i]="";
            Integer lid=wt.getWid();
            Warehouse w=warehouseMapper.selectById(lid);
            do{
                num[i]=w.getNum()+num[i];
                w=warehouseMapper.selectById(w.getParentId());
            }while (w.getParentId()!=0);
            i++;
        }
        return num;
    }

    /**
     * 出库
     * @param id
     * @param expCode
     * @param expNo
     */
    public Boolean out(Integer id,String expCode,String expNo){
        Boolean b=true;
        Goods good=goodsMapper.selectById(id);
        try{
            //清理库存
            Wzero(id);
            //更新订单状态
            if (!good.getOrderNum().equals("")){
                QueryWrapper queryWrapper=new QueryWrapper();
                queryWrapper.eq("orderNum",good.getOrderNum());
                OrderList order= orderListMapper.selectOne(queryWrapper);
                order.setTransport(true);
                order.setOrderState(7);
                orderListMapper.updateById(order);
            }
            //更新货物表
            good.setExpCode(expCode);
            good.setExpNo(expNo);
            goodsMapper.updateById(good);
            b=true;
        }catch (Exception e){
            e.printStackTrace();
            logger.info("出库失败");
            b=false;
        }
        return b;
    }

    /**
     * 根据货物的id来清理待检库的库存
     *
     * @param gid 在warehgoods表中货物的id
     */
    public void Wzero(Integer gid) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("gid", gid);
        List<WarehouseGoods> wgs = warehouseGoodsMapper.selectList(qw);
        for (WarehouseGoods wg : wgs) {
            int wid = wg.getWid();
            int id=wg.getId();
            Warehouse warehouse = warehouseMapper.selectById(wid);
            do {
                warehouse.setUsed(warehouse.getUsed() - wg.getUsed());
                warehouse.setPercentage(warehouse.getUsed() / warehouse.getCapacity() * 100);
                warehouseMapper.updateById(warehouse);
                warehouse = warehouseMapper.selectById(warehouse.getParentId());
            } while (warehouse.getParentId() != 0);
            warehouseGoodsMapper.deleteById(id);
        }
    }


    public List<OutputGoodsLog> searchByOrders(String id){
        return outputGoodsLogMapper.selectList(new QueryWrapper<OutputGoodsLog>().eq("goodsId",id));
    }

    public RespBean goodsOut(Long goodsId, String orderNum, Long orderCount){
        Double count = goodsMapper.selectOne(new QueryWrapper<Goods>().eq("id",goodsId)).getRemainCount();
        if (count < orderCount){
            return RespBean.error("货物数量不足");
        }
        OutputGoodsLog log = new OutputGoodsLog();
        log.setGoodsId(goodsId);
        log.setOrderNum(orderNum);
        log.setCreateTime(new Date());
        outputGoodsLogMapper.insert(log);
        // 更新订单状态
        orderListService.updateOrderState(OrderStateEnum.ORDER_WAREHOUSE, orderNum);
        Goods goods = new Goods();
        goods.setRemainCount(count - orderCount);
        goodsMapper.update(goods, new QueryWrapper<Goods>().eq("id",goodsId));
        return RespBean.ok("操作成功");
    }

    public List<Map<String,Object>> check(Long substationId){
        Substation substation = substationService.getById(substationId);
        List<Map<String,Object>> res = new ArrayList<>();
        if (substation.getParentId() == 0){
            // 说明是中心仓库，遍历结果
            List<Map<String, Object>> list = substationService.listByParentId(substationId);
            for (Map<String, Object> map : list) {
                res.addAll(goodsMapper.list(Long.parseLong(map.get("id").toString())));
            }
            res.addAll(goodsMapper.list(substationId));
        }else{
            res = goodsMapper.list(substationId);
        }
        return res;
    }

    public Integer supplierGoodsEnter(SupplierGoods supplierGoods, Long substationId) {
        Goods goods = new Goods();
        goods.setSubstationId(substationId);
        goods.setCount(supplierGoods.getCount().doubleValue());
        goods.setName(supplierGoods.getGoodsName());
        goods.setCodeName(supplierGoods.getCode());
        goods.setType(1);
        goods.setDate(supplierGoods.getDate());
        goods.setRemainCount(goods.getCount());
        goods.setSupplierName(supplierGoods.getSupplierName());
        User user = UserUtil.getCurrentUser();
        goods.setOrderNum(OrderNumUtil.GetRandom());
        goods.setOperator(user.getName());
        goodsMapper.insert(goods);
        return goods.getId();
    }

    public Goods searchById(Long id){
        return goodsMapper.selectById(id);
    }
}
